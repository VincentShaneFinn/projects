package edu.drexel.se320;

import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.lang.StringBuilder;
import java.lang.UnsupportedOperationException;

public class Client {

    private String lastResult;
    StringBuilder sb = null;

    public Client() {
        lastResult = "";
    }

    //pass the collaborator
    public String requestFile(String server, String file, ServerConnection conn) {
        if (server == null)
            throw new IllegalArgumentException("Null server address");
        if (file == null)
            throw new IllegalArgumentException("Null file");

//        ServerConnection conn = new ServerConnection() {
//            public boolean connectTo(String address) throws IOException {
//                throw new UnsupportedOperationException();
//            }
//            public boolean requestFileContents(String filename) throws IOException {
//                throw new UnsupportedOperationException();
//            }
//            public String read() throws IOException {
//                throw new UnsupportedOperationException();
//            }
//            public boolean moreBytes() throws IOException {
//                throw new UnsupportedOperationException();
//            }
//            public void closeConnection() throws IOException {
//                throw new UnsupportedOperationException();
//            }
//        };

        sb = new StringBuilder();

        try {
            if (conn.connectTo(server)) {
                boolean validFile = conn.requestFileContents(file);
                if (validFile) {
                    while (conn.moreBytes()) {
                        String tmp = conn.read();
                        if (tmp != null) {
                            sb.append(tmp);
                        }
                    }
                    conn.closeConnection();
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }

        String result = sb.toString();
        lastResult = result;
        return result;
    }
}

