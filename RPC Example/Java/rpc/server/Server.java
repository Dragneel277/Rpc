import java.net.*;
import java.io.*;
import java.lang.reflect.*;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 8000;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected: " + socket);

                Thread t = new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                        String functionName = in.readLine();
                        String[] argTypes = in.readLine().split(",");
                        String[] argValues = in.readLine().split(",");

                        Class<?>[] parameterTypes = new Class[argTypes.length];
                        Object[] arguments = new Object[argValues.length];

                        for (int i = 0; i < argTypes.length; i++) {
                            parameterTypes[i] = Class.forName(argTypes[i]);
                            arguments[i] = parseValue(parameterTypes[i], argValues[i]);
                        }

                        Method method = Server.class.getDeclaredMethod(functionName, parameterTypes);
                        Object result = method.invoke(null, arguments);

                        out.println(result);
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                t.start();
            }
        }
    }

    public static int addNumbers(int x, int y) {
        return x + y;
    }

    private static Object parseValue(Class<?> type, String value) {
        if (type.equals(String.class)) {
            return value;
        } else if (type.equals(int.class) || type.equals(Integer.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(float.class) || type.equals(Float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return Boolean.parseBoolean(value);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
