package spark;

import io.javalin.http.Context;
import spark.internal.NotImplementedException;

import java.util.Map;
import java.util.Set;

public class Request {

    private final Context ctx;

    public Request(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Returns the map containing all route params
     *
     * @return a map containing all route params
     */
    public Map<String, String> params() {
        return this.ctx.pathParamMap();
    }

    /**
     * Returns the value of the provided route pattern parameter.
     * Example: parameter 'name' from the following pattern: (get '/hello/:name')
     *
     * @param param the param
     * @return null if the given param is null or not found
     */
    public String params(String param) {
        String paramKey = param.startsWith(":") ? param.substring(1) : param;
        return this.ctx.pathParam(paramKey);
    }

    /**
     * @return an array containing the splat (wildcard) parameters
     */
    public String[] splat() {
        throw new NotImplementedException();
    }

    /**
     * @return request method e.g. GET, POST, PUT, ...
     */
    public String requestMethod() {
        return this.ctx.method().name().toUpperCase();
    }

    /**
     * @return the scheme
     */
    public String scheme() {
        return this.ctx.scheme();
    }

    /**
     * @return the host
     */
    public String host() {
        return this.ctx.host();
    }

    /**
     * @return the user-agent
     */
    public String userAgent() {
        return this.ctx.userAgent();
    }

    /**
     * @return the server port
     */
    public int port() {
        return this.ctx.port();
    }


    /**
     * @return the path info
     * Example return: "/example/foo"
     */
    public String pathInfo() {
        return this.ctx.path();
    }

    /**
     * @return the matched route
     * Example return: "/account/:accountId"
     */
    public String matchedPath() {
        return this.ctx.matchedPath();
    }

    /**
     * @return the servlet path
     */
    public String servletPath() {
        throw new NotImplementedException();
    }

    /**
     * @return the context path
     */
    public String contextPath() {
        return this.ctx.contextPath();
    }

    /**
     * @return the URL string
     */
    public String url() {
        return this.ctx.fullUrl();
    }

    /**
     * @return the content type of the body
     */
    public String contentType() {
        return this.ctx.contentType();
    }

    /**
     * @return the client's IP address
     */
    public String ip() {
        return this.ctx.ip();
    }

    /**
     * @return the request body sent by the client
     */
    public String body() {
        return this.ctx.body();
    }

    public byte[] bodyAsBytes() {
        return this.ctx.bodyAsBytes();
    }

    /**
     * @return the length of request.body
     */
    public int contentLength() {
        return this.ctx.contentLength();
    }

    /**
     * Gets the query param
     *
     * @param queryParam the query parameter
     * @return the value of the provided queryParam
     * Example: query parameter 'id' from the following request URI: /hello?id=foo
     */
    public String queryParams(String queryParam) {
        return this.ctx.queryParam(queryParam);
    }

    /**
     * Gets the query param and encode it
     *
     * @param queryParam the query parameter
     * @return the encode value of the provided queryParam
     * Example: query parameter 'me' from the URI: /hello?id=fool.
     */
    public String queryParamsSafe(final String queryParam) {
        throw new NotImplementedException();
    }

    /**
     * Gets the query param, or returns default value
     *
     * @param queryParam   the query parameter
     * @param defaultValue the default value
     * @return the value of the provided queryParam, or default if value is null
     * Example: query parameter 'id' from the following request URI: /hello?id=foo
     */
    public String queryParamOrDefault(String queryParam, String defaultValue) {
        String value = this.ctx.queryParam(queryParam);
        return value == null ? defaultValue : value;
    }

    /**
     * Gets all the values of the query param
     * Example: query parameter 'id' from the following request URI: /hello?id=foo&amp;id=bar
     *
     * @param queryParam the query parameter
     * @return the values of the provided queryParam, null if it doesn't exists
     */
    public String[] queryParamsValues(String queryParam) {
        return ctx.queryParams(queryParam).toArray(new String[0]);
    }

    /**
     * Gets the value for the provided header
     *
     * @param header the header
     * @return the value of the provided header
     */
    public String headers(String header) {
        return this.ctx.header(header);
    }

    /**
     * @return all query parameters
     */
    public Set<String> queryParams() {
        return this.ctx.queryParamMap().keySet();
    }

    /**
     * @return all headers
     */
    public Set<String> headers() {
        return this.ctx.headerMap().keySet();
    }

    /**
     * @return the query string
     */
    public String queryString() {
        return ctx.queryString();
    }

    /**
     * Sets an attribute on the request (can be fetched in filters/routes later in the chain)
     *
     * @param attribute The attribute
     * @param value     The attribute value
     */
    public void attribute(String attribute, Object value) {
        ctx.attribute(attribute, value);
    }

    /**
     * Gets the value of the provided attribute
     *
     * @param attribute The attribute value or null if not present
     * @param <T>       the type parameter.
     * @return the value for the provided attribute
     */
    @SuppressWarnings("unchecked")
    public <T> T attribute(String attribute) {
        return ctx.attribute(attribute);
    }


    /**
     * @return all attributes
     */
    public Set<String> attributes() {
        return ctx.attributeMap().keySet();
    }

    /**
     * @return the raw HttpServletRequest object handed in by Jetty
     */
    public Object raw() {
        // return HttpServletRequest
        return this.ctx;
    }

    /**
     * @return the query map
     */
    public QueryParamsMap queryMap() {
        return new QueryParamsMap(this.ctx.queryParamMap());
    }

    /**
     * @param key the key
     * @return the query map
     */
    public QueryParamsMap queryMap(String key) {
        throw new NotImplementedException();
    }

    /**
     * Returns the current session associated with this request,
     * or if the request does not have a session, creates one.
     *
     * @return the session associated with this request
     */
    public Session session() {
        throw new NotImplementedException();
    }

    /**
     * Returns the current session associated with this request, or if there is
     * no current session and <code>create</code> is true, returns  a new session.
     *
     * @param create <code>true</code> to create a new session for this request if necessary;
     *               <code>false</code> to return null if there's no current session
     * @return the session associated with this request or <code>null</code> if
     * <code>create</code> is <code>false</code> and the request has no valid session
     */
    public Session session(boolean create) {
        throw new NotImplementedException();
    }

    /**
     * @return request cookies (or empty Map if cookies aren't present)
     */
    public Map<String, String> cookies() {
        return this.ctx.cookieMap();
    }

    /**
     * Gets cookie by name.
     *
     * @param name name of the cookie
     * @return cookie value or null if the cookie was not found
     */
    public String cookie(String name) {
        return this.ctx.cookie(name);
    }

    /**
     * @return the part of this request's URL from the protocol name up to the query string in the first line of the HTTP request.
     */
    public String uri() {
        return ctx.url();
    }

    /**
     * @return Returns the name and version of the protocol the request uses
     */
    public String protocol() {
        return this.ctx.protocol();
    }

    /**
     * Set the session validity
     *
     * @param validSession the session validity
     */
    void validSession(boolean validSession) {
        throw new NotImplementedException();
    }

}
