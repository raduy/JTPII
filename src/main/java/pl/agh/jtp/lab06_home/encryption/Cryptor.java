package pl.agh.jtp.lab06_home.encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class represents a "Cryptor" object.
 * It can encrypt an Object to EncodedObject.
 * and decrypt an EncodedObject to an Object.
 * It use filtering CipherStreams with AES algorithm.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Cryptor implements ICryptor {
    private final static Logger LOGGER = Logger.getLogger(Cryptor.class.getName());
    //initialization vector
    private final byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
            0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
    private final IvParameterSpec IV = new IvParameterSpec(ivBytes);

    //password
    private byte[] keyBytes = "SuperExtraSafePassInPlainText:-)".getBytes(); //TODO maybe reading from license file or sth..


    @Override
    public EncodedObject encrypt(Object object) {
        byte[] encodedObjectInByteArray = null;
        try {
            SecretKey key = createKey();
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key, IV);

            ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
            CipherOutputStream cipherOS = new CipherOutputStream(byteOS, cipher);

            try(ObjectOutputStream objectOS = new ObjectOutputStream(cipherOS)) {
                objectOS.writeObject(object);
            }

            encodedObjectInByteArray = byteOS.toByteArray();

        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "No such algorithm in encrypt method!", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "No such padding in encrypt method!", e);
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "Bad key parameter in cipher.init in encrypt method!", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException while creating an ObjectInputStream!", e);
        } catch (InvalidAlgorithmParameterException e) {
            LOGGER.log(Level.SEVERE, "Bad algorithm parameter in encrypt method!", e);
        }
        return new EncodedObject(encodedObjectInByteArray);
    }

    @Override
    public Object decrypt(EncodedObject encodedObject) {
        Object result = null;
        try {
            SecretKey key = createKey();
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key, IV);

            ByteArrayInputStream byteIS = new ByteArrayInputStream(encodedObject.getData());
            CipherInputStream cipherIS = new CipherInputStream(byteIS, cipher);

            try(ObjectInputStream objectIS = new ObjectInputStream(cipherIS)) {
                result = objectIS.readObject();
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "No such algorithm in decrypt method!", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "No such padding in decrypt method!", e);
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "Bad key parameter in cipher.init in decrypt method!", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException while creating an ObjectInputStream!", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Not found Object class!", e);
        } catch (InvalidAlgorithmParameterException e) {
            LOGGER.log(Level.SEVERE, "Bad algorithm parameter in decrypt method!", e);
        }
        return result;
    }

    private SecretKeySpec createKey() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(keyBytes);
        return new SecretKeySpec(digest.digest(), 0, 16, "AES");
    }
}
