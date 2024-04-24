import java.io.*;
import java.net.*;

class revIntClient {
	public static void main(String args[]) {
		String serverAd = "localhost";
		int portNo = 7856;
		
		try {
			Socket soc = new Socket(serverAd,portNo);
			System.out.println("Server Connected.");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			PrintWriter out = new PrintWriter(soc.getOutputStream(),true);

            int ques = 456;
            String quesStr = Integer.toString(ques);
            out.println(quesStr);
			
			String ans = in.readLine();
			int w = Integer.parseInt(ans);
			System.out.println("Reversed string: " +w);

            in.close();
            out.close();
            soc.close();			
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}