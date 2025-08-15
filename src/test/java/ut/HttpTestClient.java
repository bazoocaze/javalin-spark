package ut;

import spark.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpTestClient {
    private final String basePath;
    private final HttpClient client;

    public HttpTestClient(String basePath) {
        this.basePath = basePath;
        this.client = HttpClient.newBuilder().build();
    }

    public static HttpTestClient from(Service service) {
        return new HttpTestClient("http://localhost:%d".formatted(service.port()));
    }

    public String basePath() {
        return basePath;
    }

    public HttpResponse<String> send(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> send(String path, HttpRequest.Builder request)
            throws IOException, InterruptedException {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return client.send(request.uri(URI.create(this.basePath + path)).build(), HttpResponse.BodyHandlers.ofString());
    }
}
