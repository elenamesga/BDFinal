package org.uma.bdbio2018.benchmark;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class BenchmarkException extends Exception {

    public BenchmarkException() {
    }

    public BenchmarkException(String message) {
        super(message);
    }

    public BenchmarkException(String message, Throwable cause) {
        super(message, cause);
    }

    public BenchmarkException(Throwable cause) {
        super(cause);
    }

    public BenchmarkException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
