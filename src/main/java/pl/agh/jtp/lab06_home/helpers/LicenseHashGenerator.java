package pl.agh.jtp.lab06_home.helpers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * It's a helper class, generates random hashes to represent license type.
 * To add new license kind add one more enum in LicenseType and generate a hash for it.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class LicenseHashGenerator {

    /**
     * Always generates different 512-bits hashes.
     * @return 512-bits STRING HASH
     */
    private String generateNextHash() {
        SecureRandom secureRandom = new SecureRandom();

        return new BigInteger(512, secureRandom).toString(32);
    }

    public static void main(String[] args) {
        LicenseHashGenerator licenseFileCreator = new LicenseHashGenerator();

        System.out.println(licenseFileCreator.generateNextHash());
    }
}
