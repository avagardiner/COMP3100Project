import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for clients to connect...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                String message = in.readLine();
                if (message.equals("HELO")) {
                    out.println("G'DAY");
                }

                message = in.readLine();
                if (message.equals("BYE")) {
                    out.println("BYE");
                    clientSocket.close();
                    break;
                }
            }

            System.out.println("Server stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
