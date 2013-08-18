package pl.agh.jtp.lab06_home.encryption;

/**
 *
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface ICryptor {

    /**
     * Method takes Object and encrypt them into a byte array.
     * Byte array is wrapped in a EncodedObject.
     * @param object Object to encryption
     * @return Wrapped object of encoded bytes.
     */
    EncodedObject encrypt(Object object);

    /**
     * Method takes EncodedObject and decrypt then to an Object,
     * @param encodedObject It's a byte representation of encoded object
     * @return Decrypted Object
     */
    Object decrypt(EncodedObject encodedObject);
}
