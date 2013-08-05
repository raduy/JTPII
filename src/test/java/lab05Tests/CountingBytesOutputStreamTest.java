package lab05Tests;

import org.junit.Test;
import pl.agh.jtp.lab05.CountingBytesOutputStream;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CountingBytesOutputStreamTest {
    private CountingBytesOutputStream instance;

    @Test
    public void shouldLogTenBytesAsWritten() {
        //given
        File file = new File("TenBytes.txt");

        //when
        try {
            instance = new CountingBytesOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            fail("File not found");
        }

        //then
        try {
            try {
                instance.write(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
            } finally {
                instance.close();
            }
        } catch (IOException e) {
            fail("Can't write into file");
        }

        //check also weather instance write something to the file
        try {
            FileInputStream in = new FileInputStream("TenBytes.txt");
            try {
                assertEquals(10, in.available());
            } finally {
                in.close();
            }
        } catch (IOException e) {
            fail("Bad file size, probably cause by CountingBytesOutputStream");
        }
    }
}
