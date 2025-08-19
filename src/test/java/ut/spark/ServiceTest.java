package ut.spark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spark.Service;
import ut.HttpTestClient;

import java.io.IOException;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServiceTest {

    private Service service;
    private HttpTestClient cachedClient;

    @Test
    public void validateGetMethod() throws InterruptedException, IOException {
        service.get("/hello", (req, res) -> "Hello World");

        HttpResponse<String> result = client().send("/hello", newBuilder().GET());

        assertThat(result.statusCode(), is(200));
        assertThat(result.body(), is("Hello World"));
    }

    @Test
    public void validatePostMethod() throws InterruptedException, IOException {
        service.post("/submit", (req, res) -> "Received: " + req.body());

        HttpResponse<String> result = client().send("/submit", newBuilder().POST(BodyPublishers.ofString("body1")));

        assertThat(result.statusCode(), is(200));
        assertThat(result.body(), is("Received: body1"));
    }

    @Test
    public void setStatusCode() throws IOException, InterruptedException {
        service.get("/test", (req, resp) -> {
            resp.status(204);
            return "";
        });

        HttpResponse<String> result = client().send("/test", newBuilder().GET());

        assertThat(result.statusCode(), is(204));
    }

    @Test
    public void setResponseHeader() throws IOException, InterruptedException {
        service.get("/test", (req, resp) -> {
            resp.header("Out-Header", req.headers("In-Header"));
            return "";
        });

        HttpResponse<String> result = client().send("/test", newBuilder().GET().header("In-Header", "Hello"));

        assertThat(result.headers().firstValue("Out-Header").get(), is("Hello"));
    }

    @Test
    public void returnRouterParameters() throws IOException, InterruptedException {
        service.get("/get/:param_name", (req, resp) -> "Received: " + req.params(":param_name"));

        HttpResponse<String> result = client().send("/get/abc123", newBuilder().GET());

        assertThat(result.body(), is("Received: abc123"));
    }

    @Before
    public void setup() {
        service = Service.ignite();
    }

    @After
    public void teardown() {
        if (service != null) {
            service.stop();
        }
    }

    private HttpTestClient client() {
        if (cachedClient == null) {
            service.awaitInitialization();
            cachedClient = HttpTestClient.from(service);
        }
        return cachedClient;
    }

}
