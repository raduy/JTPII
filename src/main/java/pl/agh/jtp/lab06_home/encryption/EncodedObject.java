package pl.agh.jtp.lab06_home.encryption;

import java.io.Serializable;

/**
 * Bytes representation of encoded Object.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class EncodedObject implements Serializable {
    private byte[] data;

    public EncodedObject(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
