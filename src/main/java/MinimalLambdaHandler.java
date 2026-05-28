import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Minimal AWS Lambda handler with zero dependencies.
 *
 * Compile and deploy the generated JAR directly with the runtime handler:
 * MinimalLambdaHandler::handleRequest
 */
public class MinimalLambdaHandler {

  public void handleRequest(InputStream input, OutputStream output) throws IOException {
    byte[] data = input.readAllBytes();
    String incoming = new String(data, StandardCharsets.UTF_8);

    String body = incoming == null || incoming.isBlank()
      ? "Hello from zero-dependency Java 25 Lambda"
      : "Received: " + incoming.trim();

    String json = "{\"statusCode\":200,\"body\":\"" + escapeJson(body) + "\"}\n";
    output.write(json.getBytes(StandardCharsets.UTF_8));
  }

  private static String escapeJson(String value) {
    StringBuilder sb = new StringBuilder(value.length() + 16);
    for (int i = 0; i < value.length(); i++) {
      char c = value.charAt(i);
      switch (c) {
        case '\\':
          sb.append("\\\\");
          break;
        case '"':
          sb.append("\\\"");
          break;
        case '\n':
          sb.append("\\n");
          break;
        case '\r':
          sb.append("\\r");
          break;
        case '\t':
          sb.append("\\t");
          break;
        default:
          sb.append(c);
          break;
      }
    }
    return sb.toString();
  }
}
