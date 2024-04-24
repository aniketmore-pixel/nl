import java.io.*;
import java.net.*;

class palindromeClient {
	public static void main(String args[]) {
		String serverAd = "localhost";
		int portNo = 7856;
		
		try {
			Socket soc = new Socket(serverAd,portNo);
			System.out.println("Server Connected.");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			PrintWriter out = new PrintWriter(soc.getOutputStream(),true);

            String ques = "MADAM";
            out.println(ques);
			
			String ans = in.readLine();
			int ansNo = Integer.parseInt(ans);
			
			if(ansNo==1) {
				System.out.println("String is a Palindrome.");
			}
			else {
				System.out.println("String is not a Palindrome.");
			}

            in.close();
            out.close();
            soc.close();			
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}