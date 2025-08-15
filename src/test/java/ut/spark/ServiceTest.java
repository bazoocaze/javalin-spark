package ut.spark;

import org.junit.Test;
import spark.Service;
import ut.HttpTestClient;

import java.io.IOException;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServiceTest {

    @Test
    public void validateGetMethod() throws InterruptedException, IOException {
        Service service = Service.ignite();
        service.get("/hello", (req, res) -> "Hello World");
        service.awaitInitialization();

        HttpTestClient client = HttpTestClient.from(service);
        HttpResponse<String> result = client.send("/hello", newBuilder().GET());

        assertThat(result.statusCode(), is(200));
        assertThat(result.body(), is("Hello World"));
        service.stop();
    }

    @Test
    public void validatePostMethod() throws InterruptedException, IOException {
        Service service = Service.ignite();
        service.post("/submit", (req, res) -> "Data Submitted");
        service.awaitInitialization();

        HttpTestClient client = HttpTestClient.from(service);
        HttpResponse<String> result = client.send("/submit", newBuilder().POST());

        assertThat(result.statusCode(), is(200));
        assertThat(result.body(), is("Data Submitted"));
        service.stop();
    }
}
