package tcp8;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
    	int port=5002;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port "+port+"...");
            
            while (true) {
                // Accept client connection
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Create input and output streams for communication
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String clientMessage;

                // Read messages from client and echo back
                while ((clientMessage = reader.readLine()) != null) {
                    System.out.println("Received: " + clientMessage);  // Debugging message
                    writer.println(clientMessage);  // Echo the message back to the client
                }

                System.out.println("Client disconnected");
                // Close the client socket when done
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
