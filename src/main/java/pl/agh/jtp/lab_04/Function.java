package pl.agh.jtp.lab_04;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Function<A,B> {

    B apply(A arg);
}
