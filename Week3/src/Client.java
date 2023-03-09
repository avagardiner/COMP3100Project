import java.net.*;
import java.io.*;

public class Client {
    
    public static void main(String[] args) throws IOException {
        
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            // Connect to the server on port 8000
            clientSocket = new Socket("localhost", 8000);
            System.out.println("Connected to server on port " + clientSocket.getPort());
            
            // Create the input and output streams for communication with the server
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: localhost");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost");
            System.exit(-1);
        }
        
        // Send "HELO" message to the server
        out.println("HELO");
        
        // Read the "G'DAY" message from the server
        String message = in.readLine();
        System.out.println("Server: " + message);
        
        // Send "BYE" message to the server
        out.println("BYE");
        
        // Read the "BYE" message from the server
        message = in.readLine();
        System.out.println("Server: " + message);
        
        // Close the client socket
        out.close();
        in.close();
        clientSocket.close();
    }
}