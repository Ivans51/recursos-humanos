package core.util;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Myexception extends Exception {
    public Myexception() {

    }

    public Myexception(String message) {
        super(message);
    }

    public Myexception(Throwable cause) {
        super(cause);
    }

    public Myexception(String message, Throwable cause) {
        super(message, cause);
    }
}
