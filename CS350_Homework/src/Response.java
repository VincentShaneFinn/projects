import java.util.*;
import java.io.Serializable;
public class Response implements Serializable{

	private Vector<String> response;
	
	public Response(){
		
	}
	public Response(Vector<String> r){
		response = r;
	}
	public void modifyResponse(){
		
	}
	public void setResponse(Vector<String> r){
		response = r;
	}
	public Vector<String> getResponse(){
		return response;
	}
	public boolean equals(Response r){
		return response.equals(r.getResponse());
	}
	public String display(){
		String temp = "";
		Enumeration vEnum = response.elements();
	
		while(vEnum.hasMoreElements())
		{
			temp = temp + vEnum.nextElement()+ " ";
		}
		return  temp;
	}
	
}
