package edu.drexel.se320;

import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.io.IOException;

public class MockTests {

    public MockTests() {}

    /**
     * Demonstrate a working mock from the Mockito documentation.
     * https://static.javadoc.io/org.mockito/mockito-core/2.23.0/org/mockito/Mockito.html#1
     */

    @Test
    public void testServerConnectionFailureGivesNull() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(false);

        // If you pass the mock above appropriately after refactoring, this test
        // should pass.  Until then, it will fail.
        assertNull(c.requestFile("DUMMY", "DUMMY",sc)); //now this test passes
        verify(sc).connectTo(anyString());
        verify(sc, never()).requestFileContents(anyString());
        verify(sc, never()).read();
        verify(sc, never()).moreBytes();
        verify(sc, never()).closeConnection();
    }
    
    @Test
    public void testFileNameInvalid() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(false);
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc), ""); //now this test passes
        verify(sc).connectTo(anyString());
        verify(sc).requestFileContents(anyString());
        verify(sc, never()).read();
        verify(sc, never()).moreBytes();
        verify(sc).closeConnection();
    }
    
    @Test
    public void testNonEmptyValidFile() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,false);
        when(sc.read()).thenReturn("Line One","Line Two");
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc), "Line OneLine Two"); //now this test passes
        verify(sc).connectTo(anyString());
        verify(sc).requestFileContents(anyString());
        verify(sc, times(2)).read();
        verify(sc, times(3)).moreBytes();
        verify(sc).closeConnection();
    }
    
    @Test
    public void testEmptyValidFile() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(false);
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc), ""); //now this test passes
    }
    
    @Test
    public void testFailureBeforeFullyRead() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,true,false);
        when(sc.read()).thenReturn("Line One").thenThrow(new IOException());
        
        assertNull(c.requestFile("DUMMY", "DUMMY",sc)); //now this test passes
    }
    
    @Test
    public void testConnectionClosedFailureBeforeFullyRead() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,true,false);
        when(sc.read()).thenReturn("Line One").thenThrow(new IOException());
        
        assertNull(c.requestFile("DUMMY", "DUMMY",sc)); //now this test passes
        verify(sc).connectTo(anyString());
        verify(sc).requestFileContents(anyString());
        verify(sc, times(2)).read();
        verify(sc, times(2)).moreBytes();
        verify(sc).closeConnection();
    }
    
    @Test
    public void testFileWithReset() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,true,false);
        when(sc.read()).thenReturn("Reset", "Line Two", "Line Three");
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc),"ResetLine TwoLine Three"); //now this test passes
    }
    
    @Test
    public void testReadsInOrder() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,false);
        when(sc.read()).thenReturn("Line One", "Line Two");
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc),"Line OneLine TwoLine Three"); //now this test passes
        
        verify(sc, times(2)).read();
    }
    
    @Test
    public void testReadNullAsEmptyString() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,true,false);
        when(sc.read()).thenReturn("Line One", null, "Line Three");
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc),"Line OneLine Three"); //now this test passes
    }
    
    @Test
    public void testConnectToFailure() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenThrow(new IOException());
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,false);
        when(sc.read()).thenReturn("Line One","Line Two");   
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc), null); //now this test passes
    }
    
    @Test
    public void testRequestFileContentsFailure() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenThrow(new IOException());
        when(sc.moreBytes()).thenReturn(true,true,false);
        when(sc.read()).thenReturn("Line One","Line Two");        
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc), null); //now this test passes
    }
    
    @Test
    public void testReadFailure() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,false);
        when(sc.read()).thenThrow(new IOException());        
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc), null); //now this test passes
    }
    
    @Test
    public void testMoreBytesFailure() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenThrow(new IOException());
        when(sc.read()).thenReturn("Line One","Line Two");      
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc), null); //now this test passes
    }
    
    @Test
    public void testCloseConnectionFailure() throws IOException {
        Client c = new Client();
        ServerConnection sc = mock(ServerConnection.class);
        when(sc.connectTo(anyString())).thenReturn(true);
        when(sc.requestFileContents(anyString())).thenReturn(true);
        when(sc.moreBytes()).thenReturn(true,true,false);
        when(sc.read()).thenReturn("Line One","Line Two");       
        doThrow(new IOException()).when(sc).closeConnection();
        
        assertEquals(c.requestFile("DUMMY", "DUMMY",sc), null); //now this test passes
    }
    
    
    
}
