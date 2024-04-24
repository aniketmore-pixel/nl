import java.io.*;
import java.net.*;

class revIntServer {
    public static void main(String args[]) {
		int portNo = 7856;
		
		try {
		ServerSocket soc1 = new ServerSocket(portNo);
		System.out.println("Server Started.");
		
		    while(true) { // Continuously try to establish a connection with client.
		        try {
					Socket clientSocket = soc1.accept();
					
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
					
					String a = in.readLine();
					int ln = a.length();
					int flag = 1;
					for(int i = 0; i<a.length(); i++) {
						if(a.charAt(i) != a.charAt(ln-i-1)) {
							flag = 0;
						}
					}
					
					String ans = Integer.toString(flag);
					out.println(ans);
					
					in.close();
					out.close();
					clientSocket.close();
					soc1.close();
			    }	
				catch(IOException e) {
					System.err.println(e);
				}
		    }
		}
		catch(IOException e) {
			System.err.println(e);
		}
	}
}