package pl.agh.jtp.lab03_home.visitor;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Visitable {
    /**
     *
     * @param visitor
     */
    void accept(Visitor visitor);
}
