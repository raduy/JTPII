package lab06_homeTests.encryptionTest;

import org.junit.Before;
import org.junit.Test;
import pl.agh.jtp.lab06_home.domain.IEmployee;
import pl.agh.jtp.lab06_home.domain.people.Developer;
import pl.agh.jtp.lab06_home.encryption.Cryptor;
import pl.agh.jtp.lab06_home.encryption.EncodedObject;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CryptorTest {

    private Cryptor instance;

    @Before
    public void setUp() throws Exception {
        instance = new Cryptor();
    }

    @Test
    public void shouldCorrectlyEncryptAndDecryptSimpleStringObject() throws Exception {
        //given
        String name = "Mkyoung";

        //when
        EncodedObject encodedObject= instance.encrypt(name);

        //then
        assertEquals(name, instance.decrypt(encodedObject));
    }

    @Test
    public void shouldCorrectlyCryptDeveloperObject() {
        //given
        IEmployee developer = new Developer("Jack Sparrow", "Junior Java Dev", BigDecimal.valueOf(5000));

        //when
        EncodedObject encodedObject = instance.encrypt(developer);
        developer.setSalary(BigDecimal.valueOf(4500)); //change parameter after encrypting to ensure that instance don't use actual object

        //then
        assertEquals(BigDecimal.valueOf(5000), ((IEmployee) instance.decrypt(encodedObject)).getSalary());
    }

    @Test(expected = StreamCorruptedException.class)
    public void shouldThrowExceptionWhenSomeoneTryReadEncodedObject() throws Exception {
        //given
        IEmployee developer = new Developer("Joo", "Dev", BigDecimal.valueOf(6000));

        //when
        EncodedObject encodedDev = instance.encrypt(developer);

        //then
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(encodedDev.getData()));
    }
}
