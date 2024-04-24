import java.io.*;
import java.net.*;

class factorialClient {
    public static void main(String args[]) {
	    String serverAd = "localhost";
		int serverPt = 9090;
		    
			try { // Trying to contiously connect to the server
			    Socket soc = new Socket(serverAd,serverPt);
				
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
					PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
					
					int n = 7;
					out.println(n);
					
					int a = Integer.parseInt(in.readLine());
					System.out.println(a);
					
					in.close();
		            out.close();
		            soc.close();
				}
				catch(IOException e) {
					System.err.println(e);
				}
			}
			catch(IOException e) {
				System.err.println(e);
			}
			
				
		}
}
