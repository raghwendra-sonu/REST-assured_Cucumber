package runners;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = {"stepdefs"}, plugin = {"html:target/cucumber-reports/cucumber-pretty",
"testng:target/testng-cucumber-reports/cuketestng.xml" }, features = {"src/test/resources/features"})

public class CucumberTest extends AbstractTestNGCucumberParallelTests  {
	
	private static long duration;
	
	@BeforeClass
	public static void before() {
		duration = System.currentTimeMillis();
		System.out.println("Thread Id  | Scenario Num       | Step Count");
	}
	
	@AfterClass
	public static void after() {
		duration = System.currentTimeMillis() - duration;
		System.out.println("DURATION - "+ duration);
	}
}