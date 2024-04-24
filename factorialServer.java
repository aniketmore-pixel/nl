import java.io.*;
import java.net.*;

class factorialServer {
    public static void main(String args[]) {
        int portNo = 9090;
        try {
            ServerSocket soc1 = new ServerSocket(portNo);
            System.out.println("Server Started.");

            while (true) { // Loop to continuously accept connections
                Socket clientSocket = soc1.accept();
                System.out.println("Client Connected.");

                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    int a = Integer.parseInt(in.readLine());

                    int fact = 1;
                    for (int i = 1; i <= a; i++) {
                        fact = fact * i;
                    }

                    out.println(fact);

                    // Close streams and socket after serving request
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
