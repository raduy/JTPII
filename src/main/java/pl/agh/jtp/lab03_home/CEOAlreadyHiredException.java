package pl.agh.jtp.lab03_home;

/**
 * Exception thrown when someone try hire CEO when one is already hired.
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CEOAlreadyHiredException extends RuntimeException {

    public CEOAlreadyHiredException(String message) {
        super(message);
    }

    public CEOAlreadyHiredException(Throwable throwableCause) {
        super(throwableCause);
    }
}

