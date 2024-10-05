package tcp8;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5002;

        try (Socket socket = new Socket(hostname, port)) {
            // Create input and output streams for communication
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Read message from user and send to the server
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter message: ");
            String message = userInput.readLine();

            writer.println(message);  // Send message to the server
            System.out.println("Sent to server: " + message);  // Debugging message

            // Read the server's response
            String serverResponse = reader.readLine();
            System.out.println("Echo from server: " + serverResponse);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
