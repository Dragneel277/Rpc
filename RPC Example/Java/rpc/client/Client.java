import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8000;

        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("addNumbers");
        out.println("int,Integer");
        out.println("2,3");

        String result = in.readLine();
        System.out.println(result);

        socket.close();
    }
}
