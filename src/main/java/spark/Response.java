package spark;

import io.javalin.http.Context;
import spark.internal.NotImplementedException;

import java.time.Instant;
import java.util.Date;

public class Response {

    private final Context ctx;

    public Response(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Sets the status code for the
     *
     * @param statusCode the status code
     */
    public void status(int statusCode) {
        throw new NotImplementedException();
    }

    /**
     * Returns the status code
     *
     * @return the status code
     */
    public int status() {
        throw new NotImplementedException();
    }

    /**
     * Sets the content type for the response
     *
     * @param contentType the content type
     */
    public void type(String contentType) {
        throw new NotImplementedException();
    }

    /**
     * Returns the content type
     *
     * @return the content type
     */
    public String type() {
        throw new NotImplementedException();
    }

    /**
     * Sets the body
     *
     * @param body the body
     */
    public void body(String body) {
        throw new NotImplementedException();
    }

    /**
     * returns the body
     *
     * @return the body
     */
    public String body() {
        throw new NotImplementedException();
    }

    /**
     * @return the raw response object handed in by Jetty
     */
    public Object raw() {
        // return HttpServletResponse
        throw new NotImplementedException();
    }

    /**
     * Trigger a browser redirect
     *
     * @param location Where to redirect
     */
    public void redirect(String location) {
        throw new NotImplementedException();
    }

    /**
     * Trigger a browser redirect with specific http 3XX status code.
     *
     * @param location       Where to redirect permanently
     * @param httpStatusCode the http status code
     */
    public void redirect(String location, int httpStatusCode) {
        throw new NotImplementedException();
    }

    /**
     * Adds/Sets a response header
     *
     * @param header the header
     * @param value  the value
     */
    public void header(String header, String value) {
        throw new NotImplementedException();
    }

    /**
     * Adds/Sets a response header
     *
     * @param header the header
     * @param value  the value
     */
    public void header(String header, int value) {
        throw new NotImplementedException();
    }

    /**
     * Adds/Sets a response header
     *
     * @param header the header
     * @param value  the value
     */
    public void header(String header, Date value) {
        throw new NotImplementedException();
    }

    /**
     * Adds/Sets a response header
     *
     * @param header the header
     * @param value  the value
     */
    public void header(String header, java.sql.Date value) {
        throw new NotImplementedException();
    }

    /**
     * Adds/Sets a response header
     *
     * @param header the header
     * @param value  the value
     */
    public void header(String header, Instant value) {
        throw new NotImplementedException();
    }

    /**
     * Adds not persistent cookie to the response.
     * Can be invoked multiple times to insert more than one cookie.
     *
     * @param name  name of the cookie
     * @param value value of the cookie
     */
    public void cookie(String name, String value) {
        throw new NotImplementedException();
    }

    /**
     * Adds cookie to the response. Can be invoked multiple times to insert more than one cookie.
     *
     * @param name   name of the cookie
     * @param value  value of the cookie
     * @param maxAge max age of the cookie in seconds (negative for the not persistent cookie,
     *               zero - deletes the cookie)
     */
    public void cookie(String name, String value, int maxAge) {
        throw new NotImplementedException();
    }

    /**
     * Adds cookie to the response. Can be invoked multiple times to insert more than one cookie.
     *
     * @param name    name of the cookie
     * @param value   value of the cookie
     * @param maxAge  max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     * @param secured if true : cookie will be secured
     */
    public void cookie(String name, String value, int maxAge, boolean secured) {
        throw new NotImplementedException();
    }

    /**
     * Adds cookie to the response. Can be invoked multiple times to insert more than one cookie.
     *
     * @param name     name of the cookie
     * @param value    value of the cookie
     * @param maxAge   max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     * @param secured  if true : cookie will be secured
     * @param httpOnly if true: cookie will be marked as http only
     */
    public void cookie(String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        throw new NotImplementedException();
    }

    /**
     * Adds cookie to the response. Can be invoked multiple times to insert more than one cookie.
     *
     * @param path    path of the cookie
     * @param name    name of the cookie
     * @param value   value of the cookie
     * @param maxAge  max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     * @param secured if true : cookie will be secured
     */
    public void cookie(String path, String name, String value, int maxAge, boolean secured) {
        throw new NotImplementedException();
    }

    /**
     * Adds cookie to the response. Can be invoked multiple times to insert more than one cookie.
     *
     * @param path     path of the cookie
     * @param name     name of the cookie
     * @param value    value of the cookie
     * @param maxAge   max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     * @param secured  if true : cookie will be secured
     * @param httpOnly if true: cookie will be marked as http only
     */
    public void cookie(String path, String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        throw new NotImplementedException();
    }

    /**
     * Adds cookie to the response. Can be invoked multiple times to insert more than one cookie.
     *
     * @param domain   domain of the cookie
     * @param path     path of the cookie
     * @param name     name of the cookie
     * @param value    value of the cookie
     * @param maxAge   max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     * @param secured  if true : cookie will be secured
     * @param httpOnly if true: cookie will be marked as http only
     */
    public void cookie(String domain, String path, String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        throw new NotImplementedException();
    }

    /**
     * Removes the cookie.
     *
     * @param name name of the cookie
     */
    public void removeCookie(String name) {
        throw new NotImplementedException();
    }

    /**
     * Removes the cookie with given path and name.
     *
     * @param path path of the cookie
     * @param name name of the cookie
     */
    public void removeCookie(String path, String name) {
        throw new NotImplementedException();
    }

}
