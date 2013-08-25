package pl.agh.jtp.lab06_home.IO;

import java.io.IOException;

/**
 * Interface represent object which provide simple Input and Output operation.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface IO {

    /**
     * @return Single line of text from input
     */
    String readLine();

    /**
     * Writes text to the output
     * @param text Text to write.
     */
    void write(String text);

    /**
     * Write given text and add new line sign at the end.
     * @param text Text to write.
     */
    void writeln(String text);
}
