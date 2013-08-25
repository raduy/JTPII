package pl.agh.jtp.lab06_home.versionControl;

/**
 * Determines kinds of licenses.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public enum  LicenseType {
    STANDARD {
        public String getLicenseHash() {
            //generated value
            return "nlo9d0hjs6oou5811nerba30bjubadha1ugf18v3n8t9jc0nm6uu2tu56n08gdv9jvktlfcb98r89h9sen3pt77dsdddh3vms678sk";
        }
    },
    ENTERPRISE{
        public String getLicenseHash() {
            //generated value
            return "21k8qj7bjqblfubrbj05jemebathcsbdnk0142v7vddd5uc8fe1t32ud03ur3v1op70vgr1a74g85jn971vavd7kab5gqpqjmn2i8n5";
        }
    };

    abstract public String getLicenseHash();
}
