package lab06_homeTests;

import org.junit.Before;
import org.junit.Test;
import pl.agh.jtp.lab06_home.domain.Company;
import pl.agh.jtp.lab06_home.domain.IEmployee;
import pl.agh.jtp.lab06_home.domain.people.GroupManager;
import pl.agh.jtp.lab06_home.employmentstrategy.SimpleHierarchyEmploymentStrategy;
import pl.agh.jtp.lab06_home.helpers.RandomEmployeeFactory;
import pl.agh.jtp.lab06_home.hirestrategy.TeamSizeHireStrategy;
import pl.agh.jtp.lab06_home.visitor.CompanyStructurePrintVisitor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CompanyPersistenceTest {
    private Company instance;

    @Before
    public void setUp() throws Exception {
        GroupManager ceo = new GroupManager("John", "Manager", new TeamSizeHireStrategy(10));
        instance = new Company(ceo, new SimpleHierarchyEmploymentStrategy());
        instance.setCompanyName("Microsoft");

        //add some employees
        RandomEmployeeFactory employeeFactory = new RandomEmployeeFactory();
        IEmployee[] employees = employeeFactory.getTableOfNRandomEmployees(20);
        for(IEmployee employee : employees) {
            instance.add(employee);
        }
    }

    private String createStringIdentifyingCompany(Company company) {
        CompanyStructurePrintVisitor visitor = new CompanyStructurePrintVisitor();
        company.accept(visitor);
        System.out.println(visitor.stringToPrint());
        return visitor.stringToPrint();
    }

    @Test
    public void shouldCorrectlyPersistCompanyObject() throws Exception {
        //given
        String identifyingStringBeforePersistence = createStringIdentifyingCompany(instance);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("company.txt"));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("company.txt"));

        //when
        out.writeObject(instance);
        Company deserializedCompany = (Company) in.readObject();
        String identifyingStringAfterPersistence = createStringIdentifyingCompany(deserializedCompany);

        //then
        assertEquals(identifyingStringBeforePersistence, identifyingStringAfterPersistence);
    }
}
