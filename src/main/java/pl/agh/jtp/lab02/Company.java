package pl.agh.jtp.lab02;

/**
 * Class represents simple Company structure
 *
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Company {
    private static Company instance;

    public static synchronized Company getInstance() {
        if(instance == null) {
            instance = new Company();
        }
        return instance;
    }

    private Company() {
    }

    /**
     * @param CEO is a head manager
     */
    private IManager ceo;

    void hireCEO(IManager manager) {
        if(ceo == null) {
            ceo = manager;
        } else  {
            throw new CEOAlreadyHiredException("CEO is already hired!");
        }
    }

    IManager getCEO() {
        return ceo;
    }

    @Override
    public String toString() {
        return "Company{" +
                "CEO=" + ceo +
                '}';
    }
}
