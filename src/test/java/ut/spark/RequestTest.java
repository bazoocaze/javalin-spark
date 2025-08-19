package ut.spark;

import io.javalin.http.Context;
import org.junit.Before;
import org.junit.Test;
import spark.Request;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestTest {

    private Context ctx;
    private Request request;

    @Before
    public void setUp() {
        ctx = mock(Context.class);
        request = new Request(ctx);
    }

    @Test
    public void testParams() {
        when(ctx.pathParamMap()).thenReturn(Map.of("param1", "value1"));
        assertThat(request.params(), is(Map.of("param1", "value1")));
    }

    @Test
    public void testParamsWithColon() {
        when(ctx.pathParam("param1")).thenReturn("value1");
        assertThat(request.params(":param1"), is("value1"));
    }

    @Test
    public void testRequestMethod() {
        when(ctx.method()).thenReturn(io.javalin.http.HandlerType.GET);
        assertThat(request.requestMethod(), is("GET"));
    }

    @Test
    public void testScheme() {
        when(ctx.scheme()).thenReturn("http");
        assertThat(request.scheme(), is("http"));
    }

    @Test
    public void testHost() {
        when(ctx.host()).thenReturn("localhost");
        assertThat(request.host(), is("localhost"));
    }

    @Test
    public void testUserAgent() {
        when(ctx.userAgent()).thenReturn("Mozilla/5.0");
        assertThat(request.userAgent(), is("Mozilla/5.0"));
    }

    @Test
    public void testPort() {
        when(ctx.port()).thenReturn(8080);
        assertThat(request.port(), is(8080));
    }

    @Test
    public void testPathInfo() {
        when(ctx.path()).thenReturn("/example/foo");
        assertThat(request.pathInfo(), is("/example/foo"));
    }

    @Test
    public void testMatchedPath() {
        when(ctx.matchedPath()).thenReturn("/account/:accountId");
        assertThat(request.matchedPath(), is("/account/:accountId"));
    }

    @Test
    public void testContextPath() {
        when(ctx.contextPath()).thenReturn("/context");
        assertThat(request.contextPath(), is("/context"));
    }

    @Test
    public void testUrl() {
        when(ctx.fullUrl()).thenReturn("http://localhost:8080/example/foo");
        assertThat(request.url(), is("http://localhost:8080/example/foo"));
    }

    @Test
    public void testContentType() {
        when(ctx.contentType()).thenReturn("application/json");
        assertThat(request.contentType(), is("application/json"));
    }

    @Test
    public void testIp() {
        when(ctx.ip()).thenReturn("127.0.0.1");
        assertThat(request.ip(), is("127.0.0.1"));
    }

    @Test
    public void testBody() {
        when(ctx.body()).thenReturn("{\"key\":\"value\"}");
        assertThat(request.body(), is("{\"key\":\"value\"}"));
    }

    @Test
    public void testContentLength() {
        when(ctx.contentLength()).thenReturn(10);
        assertThat(request.contentLength(), is(10));
    }

    @Test
    public void testQueryParams() {
        when(ctx.queryParam("id")).thenReturn("foo");
        assertThat(request.queryParams("id"), is("foo"));
    }

    @Test
    public void testQueryParamsValues() {
        when(ctx.queryParams("id")).thenReturn(List.of("foo", "bar"));
        assertThat(request.queryParamsValues("id"), is(new String[]{"foo", "bar"}));
    }

    @Test
    public void testHeaders() {
        when(ctx.header("Authorization")).thenReturn("Bearer token");
        assertThat(request.headers("Authorization"), is("Bearer token"));
    }

    @Test
    public void testQueryString() {
        when(ctx.queryString()).thenReturn("id=foo&name=bar");
        assertThat(request.queryString(), is("id=foo&name=bar"));
    }

    @Test
    public void testAttributes() {
        when(ctx.attributeMap()).thenReturn(Map.of("attr1", "value1"));
        assertThat(request.attributes(), is(Set.of("attr1")));
    }

    @Test
    public void testRaw() {
        assertThat(request.raw(), is(ctx));
    }
}
