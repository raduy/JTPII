package pl.agh.jtp.lab02;

/**
 * Exception thrown when someone try hire CEO when one is already hired.
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CEOAlreadyHiredException extends Exception {

    public CEOAlreadyHiredException(String message) {
        super(message);
    }

    public CEOAlreadyHiredException(Throwable throwableCause) {
        super(throwableCause);
    }
}

