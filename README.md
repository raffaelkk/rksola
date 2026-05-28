# rksola

Seed branch created for iterator token testing. A direct push to `main` is used to exercise the direct main push test path.

## Minimal Java 25 AWS Lambda (zero dependencies)

A tiny, framework-free example with a single handler method using the
InputStream/OutputStream Lambda signature:

```java
public class MinimalLambdaHandler {
  public void handleRequest(InputStream input, OutputStream output) throws IOException { ... }
}
```

Files:
- `src/main/java/MinimalLambdaHandler.java`

Compile and package only with `javac` and `jar`:

```bash
mkdir -p build/classes
javac --release 25 -d build/classes src/main/java/MinimalLambdaHandler.java
jar --create --file build/minimal-lambda-handler.jar -C build/classes .
```

Deploy with handler class `MinimalLambdaHandler::handleRequest` and runtime `java25`.
