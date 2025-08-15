package spark;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class RouteImpl implements Handler {
    private final Route route;
    private final String path;

    public RouteImpl(Route route, String path) {
        this.route = route;
        this.path = path;
    }

    public String path() {
        return path;
    }

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Request request = new Request(ctx);
        Response response = new Response(ctx);
        Object result = route.handle(request, response);
        if (result == null) {
            notFound(ctx);
        } else {
            String text = result.toString();
            if (!text.isEmpty()) {
                if (ctx.result() != null) {
                    ctx.result(ctx.result() + text);
                } else {
                    ctx.result(text);
                }
            }
        }
    }

    private void notFound(Context ctx) {
        ctx.status(404);
        ctx.result("Not found");
    }
}
