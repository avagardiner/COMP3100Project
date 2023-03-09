import java.net.*;
import java.io.*;

public class Server {
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = null;
        boolean listening = true;
        
        try {
            // Bind the server socket to port 8000
            serverSocket = new ServerSocket(8000);
            System.out.println("Server is listening on port " + serverSocket.getLocalPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port 8000.");
            System.exit(-1);
        }
        
        // Listen for incoming connections from clients
        while (listening) {
            new ServerThread(serverSocket.accept()).start();
        }
        
        // Close the server socket
        serverSocket.close();
    }
}

class ServerThread extends Thread {
    
    private Socket socket = null;
    
    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;
    }
    
    public void run() {
        
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Read the message from the client
            String message = in.readLine();
            
            // If the message is "HELO", acknowledge with "G'DAY"
            if (message.equals("HELO")) {
                out.println("G'DAY");
            }
            
            // Read the "BYE" message from the client
            message = in.readLine();
            
            // If the message is "BYE", acknowledge with "BYE" and close the socket
            if (message.equals("BYE")) {
                out.println("BYE");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}