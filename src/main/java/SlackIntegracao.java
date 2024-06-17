import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SlackIntegracao {
    private static final String WEBHOOK_URL = "https://hooks.slack.com/services/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";

    // Call sendAlert in the logic when checking if usage is above expected
    public static void sendAlert(String message) {
        try {
            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String jsonPayload = "{\"text\":\"" + message + "\"}";
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Failed to send message. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendAlert("⚠️ Performance Alert: The system is experiencing high resource usage. Check the status of CPU, RAM, disk, or other critical components to prevent potential failures.");
    }
}
