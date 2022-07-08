package testCases;



import org.testng.annotations.Test;
import testPagesPackage.CreateRole;
import testPagesPackage.updateRole;
import testBasePackage.BaseTest;

public class HomePageTest extends BaseTest {

    @Test
    public void homepageTests() {
    	CreateRole homepage = new CreateRole(getDriver());
    }
}