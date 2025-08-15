package spark;

public class HaltException extends RuntimeException {
    private final Integer status;
    private final String body;

    public HaltException(int status) {
        this.status = status;
        this.body = null;
    }

    public HaltException() {
        this.status = null;
        this.body = null;
    }

    public HaltException(String body) {
        this.status = null;
        this.body = body;
    }

    public HaltException(int status, String body) {
        this.status = status;
        this.body = body;
    }
}
