import java.io.*;
import java.net.*;
import java.util.*;

public class FocusTipServer {
    public static void main(String[] args) {
        int port = 3001;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Focus Tip Server is running on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            String line;
            while (!(line = in.readLine()).isEmpty()) {
                if (line.contains("GET /tip")) break;
            }

            String tip = getRandomTip("focus-tips.txt");
            String jsonResponse = "{\"tip\": \"" + tip + "\"}";

            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: application/json\r\n");
            out.write("Access-Control-Allow-Origin: *\r\n");
            out.write("Content-Length: " + jsonResponse.length() + "\r\n");
            out.write("\r\n");
            out.write(jsonResponse);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomTip(String fileName) throws IOException {
        List<String> tips = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String tip;
            while ((tip = reader.readLine()) != null) {
                tips.add(tip);
            }
        }
        return tips.isEmpty() ? "Stay focused and do your best!" : tips.get(new Random().nextInt(tips.size()));
    }
}
