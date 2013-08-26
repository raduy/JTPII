package pl.agh.jtp.lab06_home.versionControl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class control version of running application based on information in license file.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class VersionController {
    private static final Logger LOGGER = Logger.getLogger(VersionController.class.getName());
    private LicenseType version;

    public VersionController() {
        loadApplicationVersion();
    }

    public LicenseType getVersion() {
        return version;
    }

    private void loadApplicationVersion() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("license.lic"))) {
            String licenseHash = bufferedReader.readLine();

            for(LicenseType licenseType : LicenseType.values()) {
                if(licenseHash.equals(licenseType.getLicenseHash())) {
                    version = licenseType;
                    LOGGER.log(Level.INFO, "Loaded license: " + version);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "License File not found!!!", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem during reading an license file!", e);
        }
    }
}
