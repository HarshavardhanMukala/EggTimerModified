package CucumberOptions;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.aventstack.extentreports.*;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/feature",
		glue="stepDefenition")


public class TestRunner extends AbstractTestNGCucumberTests	 {
	
	
	/*@AfterClass
	public static void reportSetup()
	{
		
	}*/

}
