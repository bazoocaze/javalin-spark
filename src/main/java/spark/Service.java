package spark;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import spark.embeddedserver.EmbeddedServers;
import spark.internal.NotImplementedException;
import spark.internal.ResettableBarrier;
import spark.route.HttpMethod;
import spark.routematch.RouteMatch;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Service extends Routable {
    public static final int SPARK_DEFAULT_PORT = 4567;
    public final Redirect redirect;
    public final StaticFiles staticFiles;
    private Javalin javalin = null;
    private int port = SPARK_DEFAULT_PORT;
    private boolean initialized = false;

    private ResettableBarrier startWaiter = new ResettableBarrier();
    private ResettableBarrier stopWaiter = new ResettableBarrier();

    private Service() {
        redirect = Redirect.create(this);
        staticFiles = new StaticFiles();
    }

    /**
     * Creates a new Service (a Spark instance). This should be used instead of the static API if the user wants
     * multiple services in one process.
     *
     * @return the newly created object
     */
    public static Service ignite() {
        return new Service();
    }

    /**
     * Set the identifier used to select the EmbeddedServer;
     * null for the default.
     *
     * @param obj the identifier passed to {@link EmbeddedServers}.
     */
    public synchronized void embeddedServerIdentifier(Object obj) {
        throw new NotImplementedException();
    }

    /**
     * Get the identifier used to select the EmbeddedServer;
     * null for the default.
     */
    public synchronized Object embeddedServerIdentifier() {
        throw new NotImplementedException();
    }

    /**
     * Set the IP address that Spark should listen on. If not called the default
     * address is '0.0.0.0'. This has to be called before any route mapping is
     * done.
     *
     * @param ipAddress The ipAddress
     * @return the object with IP address set
     */
    public synchronized Service ipAddress(String ipAddress) {
        throw new NotImplementedException();
    }

    /**
     * Set the port that Spark should listen on. If not called the default port
     * is 4567. This has to be called before any route mapping is done.
     * If provided port = 0 then the an arbitrary available port will be used.
     *
     * @param port The port number
     * @return the object with port set
     */
    public synchronized Service port(int port) {
        validateNotInitialized();
        this.port = port;
        return this;
    }

    /**
     * Retrieves the port that Spark is listening on.
     *
     * @return The port Spark server is listening on.
     * @throws IllegalStateException when the server is not started
     */
    public synchronized int port() {
        if (initialized) {
            return this.javalin.port();
        } else {
            return this.port;
        }
    }

    /**
     * Set the connection to be secure, using the specified keystore and
     * truststore. This has to be called before any route mapping is done. You
     * have to supply a keystore file, truststore file is optional (keystore
     * will be reused). By default, client certificates are not checked.
     * This method is only relevant when using embedded Jetty servers. It should
     * not be used if you are using Servlets, where you will need to secure the
     * connection in the servlet container
     *
     * @param keystoreFile       The keystore file location as string
     * @param keystorePassword   the password for the keystore
     * @param truststoreFile     the truststore file location as string, leave null to reuse
     *                           keystore
     * @param truststorePassword the trust store password
     * @return the object with connection set to be secure
     */
    public synchronized Service secure(String keystoreFile,
                                       String keystorePassword,
                                       String truststoreFile,
                                       String truststorePassword) {
        return secure(keystoreFile, keystorePassword, null, truststoreFile, truststorePassword, false);
    }

    /**
     * Set the connection to be secure, using the specified keystore and
     * truststore. This has to be called before any route mapping is done. You
     * have to supply a keystore file, truststore file is optional (keystore
     * will be reused). By default, client certificates are not checked.
     * This method is only relevant when using embedded Jetty servers. It should
     * not be used if you are using Servlets, where you will need to secure the
     * connection in the servlet container
     *
     * @param keystoreFile       The keystore file location as string
     * @param keystorePassword   the password for the keystore
     * @param certAlias          the default certificate Alias
     * @param truststoreFile     the truststore file location as string, leave null to reuse
     *                           keystore
     * @param truststorePassword the trust store password
     * @return the object with connection set to be secure
     */
    public synchronized Service secure(String keystoreFile,
                                       String keystorePassword,
                                       String certAlias,
                                       String truststoreFile,
                                       String truststorePassword) {
        return secure(keystoreFile, keystorePassword, certAlias, truststoreFile, truststorePassword, false);
    }

    /**
     * Set the connection to be secure, using the specified keystore and
     * truststore. This has to be called before any route mapping is done. You
     * have to supply a keystore file, truststore file is optional (keystore
     * will be reused).
     * This method is only relevant when using embedded Jetty servers. It should
     * not be used if you are using Servlets, where you will need to secure the
     * connection in the servlet container
     *
     * @param keystoreFile       The keystore file location as string
     * @param keystorePassword   the password for the keystore
     * @param truststoreFile     the truststore file location as string, leave null to reuse
     *                           keystore
     * @param needsClientCert    Whether to require client certificate to be supplied in
     *                           request
     * @param truststorePassword the trust store password
     * @return the object with connection set to be secure
     */
    public synchronized Service secure(String keystoreFile,
                                       String keystorePassword,
                                       String truststoreFile,
                                       String truststorePassword,
                                       boolean needsClientCert) {
        return secure(keystoreFile, keystorePassword, null, truststoreFile, truststorePassword, needsClientCert);
    }

    /**
     * Set the connection to be secure, using the specified keystore and
     * truststore. This has to be called before any route mapping is done. You
     * have to supply a keystore file, truststore file is optional (keystore
     * will be reused).
     * This method is only relevant when using embedded Jetty servers. It should
     * not be used if you are using Servlets, where you will need to secure the
     * connection in the servlet container
     *
     * @param keystoreFile       The keystore file location as string
     * @param keystorePassword   the password for the keystore
     * @param certAlias          the default certificate Alias
     * @param truststoreFile     the truststore file location as string, leave null to reuse
     *                           keystore
     * @param needsClientCert    Whether to require client certificate to be supplied in
     *                           request
     * @param truststorePassword the trust store password
     * @return the object with connection set to be secure
     */
    public synchronized Service secure(String keystoreFile,
                                       String keystorePassword,
                                       String certAlias,
                                       String truststoreFile,
                                       String truststorePassword,
                                       boolean needsClientCert) {
        throw new NotImplementedException();
    }

    /**
     * Configures the embedded web server's thread pool.
     *
     * @param maxThreads max nbr of threads.
     * @return the object with the embedded web server's thread pool configured
     */
    public synchronized Service threadPool(int maxThreads) {
        return threadPool(maxThreads, -1, -1);
    }

    /**
     * Configures the embedded web server's thread pool.
     *
     * @param maxThreads        max nbr of threads.
     * @param minThreads        min nbr of threads.
     * @param idleTimeoutMillis thread idle timeout (ms).
     * @return the object with the embedded web server's thread pool configured
     */
    public synchronized Service threadPool(int maxThreads, int minThreads, int idleTimeoutMillis) {
        throw new NotImplementedException();
    }

    /**
     * Sets the folder in classpath serving static files. Observe: this method
     * must be called before all other methods.
     *
     * @param folder the folder in classpath.
     * @return the object with folder set
     */
    public synchronized Service staticFileLocation(String folder) {
        throw new NotImplementedException();
    }

    /**
     * Sets the external folder serving static files. <b>Observe: this method
     * must be called before all other methods.</b>
     *
     * @param externalFolder the external folder serving static files.
     * @return the object with external folder set
     */
    public synchronized Service externalStaticFileLocation(String externalFolder) {
        throw new NotImplementedException();
    }

    /**
     * Unmaps a particular route from the collection of those that have been previously routed.
     * Search for previously established routes using the given path and unmaps any matches that are found.
     *
     * @param path the route path
     * @return <tt>true</tt> if this is a matching route which has been previously routed
     * @throws IllegalArgumentException if <tt>path</tt> is null or blank
     */
    public boolean unmap(String path) {
        throw new NotImplementedException();
    }

    /**
     * Unmaps a particular route from the collection of those that have been previously routed.
     * Search for previously established routes using the given path and HTTP method, unmaps any
     * matches that are found.
     *
     * @param path       the route path
     * @param httpMethod the http method
     * @return <tt>true</tt> if this is a matching route that has been previously routed
     * @throws IllegalArgumentException if <tt>path</tt> is null or blank or if <tt>httpMethod</tt> is null, blank,
     *                                  or an invalid HTTP method
     */
    public boolean unmap(String path, String httpMethod) {
        throw new NotImplementedException();
    }

    /**
     * Maps the given path to the given WebSocket handler class.
     * <p>
     * This is currently only available in the embedded server mode.
     *
     * @param path         the WebSocket path.
     * @param handlerClass the handler class that will manage the WebSocket connection to the given path.
     */
    public void webSocket(String path, Class<?> handlerClass) {
        throw new NotImplementedException();
    }

    /**
     * Maps the given path to the given WebSocket handler instance.
     * <p>
     * This is currently only available in the embedded server mode.
     *
     * @param path    the WebSocket path.
     * @param handler the handler instance that will manage the WebSocket connection to the given path.
     */
    public void webSocket(String path, Object handler) {
        throw new NotImplementedException();
    }

    /**
     * Sets the max idle timeout in milliseconds for WebSocket connections.
     *
     * @param timeoutMillis The max idle timeout in milliseconds.
     * @return the object with max idle timeout set for WebSocket connections
     */
    public synchronized Service webSocketIdleTimeoutMillis(long timeoutMillis) {
        throw new NotImplementedException();
    }

    /**
     * Maps 404 errors to the provided custom page
     *
     * @param page the custom 404 error page.
     */
    public synchronized void notFound(String page) {
        throw new NotImplementedException();
    }

    /**
     * Maps 500 internal server errors to the provided custom page
     *
     * @param page the custom 500 internal server error page.
     */
    public synchronized void internalServerError(String page) {
        throw new NotImplementedException();
    }

    /**
     * Maps 404 errors to the provided route.
     */
    public synchronized void notFound(Route route) {
        throw new NotImplementedException();
    }

    /**
     * Maps 500 internal server errors to the provided route.
     */
    public synchronized void internalServerError(Route route) {
        throw new NotImplementedException();
    }

    /**
     * Waits for the spark server to be initialized.
     * If it's already initialized will return immediately
     */
    public void awaitInitialization() {
        startWaiter.pass();
    }

    /**
     * Stops the Spark server and clears all routes.
     */
    public synchronized void stop() {
        if (!initialized) {
            return;
        }
        stopWaiter.block();
        try {
            javalin.stop();
        } finally {
            this.initialized = false;
            stopWaiter.unblock();
        }
    }

    /**
     * Waits for the Spark server to stop.
     * <b>Warning:</b> this method should not be called from a request handler.
     */
    public void awaitStop() {
        stopWaiter.pass();
    }

    /**
     * Add a path-prefix to the routes declared in the routeGroup
     * The path() method adds a path-fragment to a path-stack, adds
     * routes from the routeGroup, then pops the path-fragment again.
     * It's used for separating routes into groups, for example:
     * path("/api/email", () -> {
     * ....post("/add",       EmailApi::addEmail);
     * ....put("/change",     EmailApi::changeEmail);
     * ....etc
     * });
     * Multiple path() calls can be nested.
     *
     * @param path       the path to prefix routes with
     * @param routeGroup group of routes (can also contain path() calls)
     */
    public void path(String path, RouteGroup routeGroup) {
        throw new NotImplementedException();
    }

    public String getPaths() {
        throw new NotImplementedException();
    }

    /**
     * @return all routes information from this service
     */
    public List<RouteMatch> routes() {
        throw new NotImplementedException();
    }

    @Override
    public void addRoute(HttpMethod httpMethod, RouteImpl route) {
        init();
        if (httpMethod == HttpMethod.get) {
            this.javalin.get(route.path(), route);
        } else {
            throw new NotImplementedException();
        }
    }

    @Override
    public void addFilter(HttpMethod httpMethod, FilterImpl filter) {
        init();
        throw new NotImplementedException();
    }

    @Override
    @Deprecated
    public void addRoute(String httpMethod, RouteImpl route) {
        this.addRoute(HttpMethod.get(httpMethod), route);
    }

    @Override
    @Deprecated
    public void addFilter(String httpMethod, FilterImpl filter) {
        addFilter(HttpMethod.get(httpMethod), filter);
    }

    public synchronized void init() {
        startWaiter.block();
        try {
            this.javalin = Javalin.create(new ConfigConsumer()).start(port);
            System.out.println("Init finished");
        } finally {
            startWaiter.unblock();
            this.initialized = true;
        }
    }

    /**
     * @return The approximate number of currently active threads in the embedded Jetty server
     */
    public synchronized int activeThreadCount() {
        return 0;
    }

    //////////////////////////////////////////////////
    // EXCEPTION mapper
    //////////////////////////////////////////////////

    /**
     * Maps an exception handler to be executed when an exception occurs during routing
     *
     * @param exceptionClass the exception class
     * @param handler        The handler
     */
    public synchronized <T extends Exception> void exception(Class<T> exceptionClass, ExceptionHandler<? super T> handler) {
    }

    //////////////////////////////////////////////////
    // HALT methods
    //////////////////////////////////////////////////

    /**
     * Immediately stops a request within a filter or route
     * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
     * halt will not work
     *
     * @return HaltException object
     */
    public HaltException halt() {
        throw new HaltException();
    }

    /**
     * Immediately stops a request within a filter or route with specified status code
     * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
     * halt will not work
     *
     * @param status the status code
     * @return HaltException object with status code set
     */
    public HaltException halt(int status) {
        throw new HaltException(status);
    }

    /**
     * Immediately stops a request within a filter or route with specified body content
     * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
     * halt will not work
     *
     * @param body The body content
     * @return HaltException object with body set
     */
    public HaltException halt(String body) {
        throw new HaltException(body);
    }

    /**
     * Immediately stops a request within a filter or route with specified status code and body content
     * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
     * halt will not work
     *
     * @param status The status code
     * @param body   The body content
     * @return HaltException object with status and body set
     */
    public HaltException halt(int status, String body) {
        throw new HaltException(status, body);
    }

    /**
     * Sets Spark to trust the HTTP headers that are commonly used in reverse proxies.
     * More info at https://www.eclipse.org/jetty/javadoc/current/org/eclipse/jetty/server/ForwardedRequestCustomizer.html
     */
    public synchronized Service trustForwardHeaders() {
        return this;
    }

    /**
     * Sets Spark to NOT trust the HTTP headers that are commonly used in reverse proxies.
     * More info at https://www.eclipse.org/jetty/javadoc/current/org/eclipse/jetty/server/ForwardedRequestCustomizer.html
     */
    public synchronized Service untrustForwardHeaders() {
        return this;
    }

    /**
     * Overrides default exception handler during initialization phase
     *
     * @param initExceptionHandler The custom init exception handler
     */
    public void initExceptionHandler(Consumer<ExceptionHandler> initExceptionHandler) {
        throw new NotImplementedException();
    }

    private void validateNotInitialized() {
        if (initialized) {
            throw new IllegalStateException("Service is already initialized");
        }
    }

    /**
     * Provides static files utility methods.
     */
    public final class StaticFiles {

        /**
         * Sets the folder in classpath serving static files. Observe: this method
         * must be called before all other methods.
         *
         * @param folder the folder in classpath.
         */
        public void location(String folder) {
            staticFileLocation(folder);
        }

        /**
         * Sets the external folder serving static files. <b>Observe: this method
         * must be called before all other methods.</b>
         *
         * @param externalFolder the external folder serving static files.
         */
        public void externalLocation(String externalFolder) {
            externalStaticFileLocation(externalFolder);
        }

        /**
         * Puts custom headers for static resources. If the headers previously contained mapping for
         * a specific key in the provided headers map, the old value is replaced by the specified value.
         *
         * @param headers the headers to set on static resources
         */
        public void headers(Map<String, String> headers) {
            throw new NotImplementedException();
        }

        /**
         * Puts custom header for static resources. If the headers previously contained a mapping for
         * the key, the old value is replaced by the specified value.
         *
         * @param key   the key
         * @param value the value
         */
        public void header(String key, String value) {
            throw new NotImplementedException();
        }

        /**
         * Sets the expire-time for static resources
         *
         * @param seconds the expire time in seconds
         */
        @Experimental("Functionality will not be removed. The API might change")
        public void expireTime(long seconds) {
            throw new NotImplementedException();
        }

        /**
         * Maps an extension to a mime-type. This will overwrite any previous mappings.
         *
         * @param extension the extension to be mapped
         * @param mimeType  the mime-type for the extension
         */
        public void registerMimeType(String extension, String mimeType) {
            throw new NotImplementedException();
        }

        /**
         * Disables the automatic setting of Content-Type header made from a guess based on extension.
         */
        public void disableMimeTypeGuessing() {
            throw new NotImplementedException();
        }

    }

    private class ConfigConsumer implements Consumer<JavalinConfig> {
        @Override
        public void accept(JavalinConfig javalinConfig) {
            javalinConfig.events(c -> {
                c.serverStarting(() -> System.out.println("[Server starting]"));
                c.serverStarted(() -> System.out.println("[Server started]"));
                c.serverStartFailed(() -> System.out.println("[Server start failed]"));
                c.serverStopping(() -> System.out.println("[Server stopping]"));
                c.serverStopped(() -> System.out.println("[Server stopped]"));
                c.serverStopFailed(() -> System.out.println("[Server stop failed]"));
            });
        }
    }
}
