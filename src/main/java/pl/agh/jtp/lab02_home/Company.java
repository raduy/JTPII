package pl.agh.jtp.lab02_home;

/**
 * Class represents simple Company structure
 *
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Company {
    private static Company instance = new Company();

    public static Company getInstance() {
        return instance;
    }

    private Company() {
    }

    /**
     * @param CEO is a head manager
     */
    private IManager ceo;

    void hireCEO(IManager manager) {
        ceo = manager;
    }

    IManager getCEO() {
        return ceo;
    }
}
