package spark;

import spark.internal.NotImplementedException;
import spark.route.HttpMethod;

public abstract class Routable {

    /**
     * Adds a route
     *
     * @param httpMethod the HTTP method
     * @param route      the route implementation
     */
    protected abstract void addRoute(HttpMethod httpMethod, RouteImpl route);

    @Deprecated
    protected abstract void addRoute(String httpMethod, RouteImpl route);

    /**
     * Adds a filter
     *
     * @param httpMethod the HTTP method
     * @param filter     the route implementation
     */
    protected abstract void addFilter(HttpMethod httpMethod, FilterImpl filter);

    @Deprecated
    protected abstract void addFilter(String httpMethod, FilterImpl filter);

    /////////////////////////////
    // Default implementations //

    /**
     * Map the route for HTTP GET requests
     *
     * @param path  the path
     * @param route The route
     */
    public void get(String path, Route route) {
        this.addRoute(HttpMethod.get, new RouteImpl(route, path));
    }

    /**
     * Map the route for HTTP POST requests
     *
     * @param path  the path
     * @param route The route
     */
    public void post(String path, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PUT requests
     *
     * @param path  the path
     * @param route The route
     */
    public void put(String path, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PATCH requests
     *
     * @param path  the path
     * @param route The route
     */
    public void patch(String path, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP DELETE requests
     *
     * @param path  the path
     * @param route The route
     */
    public void delete(String path, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP HEAD requests
     *
     * @param path  the path
     * @param route The route
     */
    public void head(String path, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP TRACE requests
     *
     * @param path  the path
     * @param route The route
     */
    public void trace(String path, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP CONNECT requests
     *
     * @param path  the path
     * @param route The route
     */
    public void connect(String path, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP OPTIONS requests
     *
     * @param path  the path
     * @param route The route
     */
    public void options(String path, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Maps a filter to be executed before any matching routes
     *
     * @param path   the path
     * @param filter The filter
     */
    public void before(String path, Filter filter) {
        addFilter(HttpMethod.before, FilterImpl.create(path, filter));
    }

    /**
     * Maps a filter to be executed after any matching routes
     *
     * @param path   the path
     * @param filter The filter
     */
    public void after(String path, Filter filter) {
        addFilter(HttpMethod.after, FilterImpl.create(path, filter));
    }

    //////////////////////////////////////////////////
    // BEGIN route/filter mapping with accept type
    //////////////////////////////////////////////////

    /**
     * Map the route for HTTP GET requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void get(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP POST requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void post(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PUT requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void put(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PATCH requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void patch(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP DELETE requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void delete(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP HEAD requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void head(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP TRACE requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void trace(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP CONNECT requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void connect(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP OPTIONS requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     */
    public void options(String path, String acceptType, Route route) {
        throw new NotImplementedException();
    }


    /**
     * Maps a filter to be executed before any matching routes
     *
     * @param filter The filter
     */
    public void before(Filter filter) {
        throw new NotImplementedException();
    }

    /**
     * Maps a filter to be executed after any matching routes
     *
     * @param filter The filter
     */
    public void after(Filter filter) {
        throw new NotImplementedException();
    }

    /**
     * Maps a filter to be executed before any matching routes
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param filter     The filter
     */
    public void before(String path, String acceptType, Filter filter) {
        addFilter(HttpMethod.before, FilterImpl.create(path, acceptType, filter));
    }

    /**
     * Maps a filter to be executed after any matching routes
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param filter     The filter
     */
    public void after(String path, String acceptType, Filter filter) {
        addFilter(HttpMethod.after, FilterImpl.create(path, acceptType, filter));
    }

    /**
     * Maps a filter to be executed after any matching routes even if the route throws any exception
     *
     * @param filter The filter
     */
    public void afterAfter(Filter filter) {
        throw new NotImplementedException();
    }

    /**
     * Maps a filter to be executed after any matching routes even if the route throws any exception
     *
     * @param filter The filter
     */
    public void afterAfter(String path, Filter filter) {
        addFilter(HttpMethod.afterafter, FilterImpl.create(path, filter));
    }

    //////////////////////////////////////////////////
    // END route/filter mapping with accept type
    //////////////////////////////////////////////////

    //////////////////////////////////////////////////
    // BEGIN Template View Routes
    //////////////////////////////////////////////////

    /**
     * Map the route for HTTP GET requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void get(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP GET requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void get(String path,
                    String acceptType,
                    TemplateViewRoute route,
                    TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP POST requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void post(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP POST requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void post(String path,
                     String acceptType,
                     TemplateViewRoute route,
                     TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PUT requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void put(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PUT requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void put(String path,
                    String acceptType,
                    TemplateViewRoute route,
                    TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP DELETE requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void delete(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP DELETE requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void delete(String path,
                       String acceptType,
                       TemplateViewRoute route,
                       TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PATCH requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void patch(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PATCH requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void patch(String path,
                      String acceptType,
                      TemplateViewRoute route,
                      TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP HEAD requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void head(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP HEAD requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void head(String path,
                     String acceptType,
                     TemplateViewRoute route,
                     TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP TRACE requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void trace(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP TRACE requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void trace(String path,
                      String acceptType,
                      TemplateViewRoute route,
                      TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP CONNECT requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void connect(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP CONNECT requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void connect(String path,
                        String acceptType,
                        TemplateViewRoute route,
                        TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP OPTIONS requests
     *
     * @param path   the path
     * @param route  The route
     * @param engine the template engine
     */
    public void options(String path, TemplateViewRoute route, TemplateEngine engine) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP OPTIONS requests
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      The route
     * @param engine     the template engine
     */
    public void options(String path,
                        String acceptType,
                        TemplateViewRoute route,
                        TemplateEngine engine) {
        throw new NotImplementedException();
    }

    //////////////////////////////////////////////////
    // END Template View Routes
    //////////////////////////////////////////////////

    //////////////////////////////////////////////////
    // BEGIN Response Transforming Routes
    //////////////////////////////////////////////////

    /**
     * Map the route for HTTP GET requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void get(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP GET requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void get(String path, String acceptType, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP POST requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void post(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP POST requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void post(String path, String acceptType, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PUT requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void put(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PUT requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void put(String path, String acceptType, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP DELETE requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void delete(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP DELETE requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void delete(String path,
                       String acceptType,
                       Route route,
                       ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP HEAD requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void head(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP HEAD requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void head(String path, String acceptType, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP CONNECT requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void connect(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP CONNECT requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void connect(String path,
                        String acceptType,
                        Route route,
                        ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP TRACE requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void trace(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP TRACE requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void trace(String path,
                      String acceptType,
                      Route route,
                      ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP OPTIONS requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void options(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP OPTIONS requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void options(String path,
                        String acceptType,
                        Route route,
                        ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PATCH requests
     *
     * @param path        the path
     * @param route       The route
     * @param transformer the response transformer
     */
    public void patch(String path, Route route, ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Map the route for HTTP PATCH requests
     *
     * @param path        the path
     * @param acceptType  the accept type
     * @param route       The route
     * @param transformer the response transformer
     */
    public void patch(String path,
                      String acceptType,
                      Route route,
                      ResponseTransformer transformer) {
        throw new NotImplementedException();
    }

    /**
     * Sets default response transformer
     *
     * @param transformer
     */
    public void defaultResponseTransformer(ResponseTransformer transformer) {
        throw new NotImplementedException();
    }
}
