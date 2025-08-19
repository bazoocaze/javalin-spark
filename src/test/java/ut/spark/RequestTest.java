package ut.spark;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HandlerType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import spark.Request;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestTest {

    @Test
    public void returnRequestMethod() {
        when(ctx.method()).thenReturn(HandlerType.GET);

        assertThat(request.requestMethod(), is("GET"));
    }

    private final Context ctx = mock(Context.class);
    private final Request request = new Request(ctx);
}