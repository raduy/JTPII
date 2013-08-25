package pl.agh.jtp.lab06_home.IO;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of {@link IO} interface, execute IO operation with System Console.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class ConsoleIO implements IO {
    private final static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    private final static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger LOGGER = Logger.getLogger(ConsoleIO.class.getName());

    @Override
    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem during reading a new line!", e);
        }
        return null;
    }

    @Override
    public void write(String text) {
        try {
            out.write(text);
            out.flush();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem during writing a text!", e);
        }
    }

    @Override
    public void writeln(String text) {
        try {
            out.write(text);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem during writing a new line!", e);
        }
    }
}
