import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connected to server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            out.println("HELO");
            String message = in.readLine();
            System.out.println("Server says: " + message);

            out.println("BYE");
            message = in.readLine();
            System.out.println("Server says: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client stopped.");
    }
}
