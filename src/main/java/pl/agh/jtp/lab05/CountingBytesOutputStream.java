package pl.agh.jtp.lab05;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * OutputStream decorator - log number of written bytes before closing.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CountingBytesOutputStream extends OutputStream {
    private int counter;
    private OutputStream outputStream;

    private static final Logger LOGGER = Logger.getLogger("CountingBytesOutputStream");

    public CountingBytesOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(int i) throws IOException {
        outputStream.write(i);
        counter++;
    }

    @Override
    public void close() throws IOException {
        LOGGER.info("OutputStream writes: " + counter + " bytes.");
    }
}
