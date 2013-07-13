package pl.agh.jtp.lab02;

/**
 * Class represents simple Company structure
 *
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Company {
    private static Company instance = new Company();

    public static synchronized Company getInstance() {
        return instance;
    }

    private Company() {
    }

    /**
     * @param CEO is a head manager
     */
    private IManager ceo;

    void hireCEO(IManager manager) throws CEOAlreadyHiredException {
        if(ceo == null) {
            ceo = manager;
            manager.setSupervisor(null);
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
