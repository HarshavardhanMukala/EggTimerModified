package stepDefenition;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;
import junit.framework.Assert;
import resources.Base;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;


import org.apache.logging.log4j.LogManager;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CounterPage;
import PageObjects.HomePage;

@RunWith(Cucumber.class)
public class stepDefenition extends Base{

	public static Logger Log=LogManager.getLogger(stepDefenition.class.getName());
	
	
    @Given("^User has the URl for the egg timer website$")
    public void user_has_the_url_for_the_egg_timer_website() throws Throwable {
    	driver=InitializeBrowser();//depends on the driver you chose, call that driver
    	Log.info("User has copied the URL");
  	
    }

    @Given("^User is landed into the page$")
    public void user_is_landed_into_the_page() throws Throwable {
    	driver=InitializeBrowser();
    	driver.get(prop.getProperty("URL"));
    	driver.manage().window().maximize();
    	HomePage hp=new HomePage(driver);
    	if(hp.StartTimeCheck().isDisplayed())
    	{
    		Log.info("User is in homepage now");
    	}
    }

    @Given("^User navigated to the counter page$")
    public void user_navigated_to_the_counter_page() throws Throwable {
    	driver=InitializeBrowser();
    	driver.get(prop.getProperty("URL"));
    	driver.manage().window().maximize();
    	HomePage hp=new HomePage(driver);
    	CounterPage cp=new CounterPage(driver);
    	hp.StartTimer().clear();
    	hp.StartTimer().sendKeys(prop.getProperty("Time"));
    	hp.Go().click();
    	
    	WebDriverWait d=new WebDriverWait(driver,5);
		d.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressText")));
       
       Log.info("User navigated to the counter page");
     
    }

    

    @When("^user enters the URL and search$")
    public void user_enters_the_url_and_search() throws Throwable {
    	driver.get(prop.getProperty("URL"));
    	driver.manage().window().maximize();
    	Log.info("User entered the URL in the Browser and clicked on search");
    }

    @When("^user enters the time in the start a timer field$")
    public void user_enters_the_time_in_the_start_a_timer_field() throws Throwable {
    	HomePage hp=new HomePage(driver);
    	hp.StartTimer().clear();
    	hp.StartTimer().sendKeys(prop.getProperty("Time"));
    	hp.Go().click();
    	
    	WebDriverWait d=new WebDriverWait(driver,5);
		d.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressText")));
		Log.info("User entered the time and clicked on go successfully");
    }

    @When("^counter started$")
    public void counter_started() throws Throwable {
       
        Log.info("Counter started counting");
    }

    

    @Then("^user should be navigated to the egg timer website$")
    public void user_should_be_navigated_to_the_egg_timer_website() throws Throwable {
    	HomePage hp=new HomePage(driver);
    	if(hp.StartTimeCheck().isDisplayed())
    	{
    	Log.info("User is landed in the homepage of Egg timer successfully");
    	}
    	
    	driver.quit();
    }

    @Then("^page should navigate to the time counter$")
    public void page_should_navigate_to_the_time_counter() throws Throwable {
       CounterPage cp=new CounterPage(driver);
       if(cp.StartTimer1().isDisplayed())
       {
    	   Log.info("user is navigated to counter page successfully");
       }
       driver.quit();
    }

    @Then("^check if the counter period is started from the period mentioned in the home page$")
    public void check_if_the_counter_period_is_started_from_the_period_mentioned_in_the_home_page() throws Throwable {
    	 CounterPage cp=new CounterPage(driver); 
    	
    	String c=cp.StartTimer1().getText();
    	 String e=prop.getProperty("Time");
    	
    	 
    	if(c==e)
    	{
    		Log.info("Counter started from the specified time period in the home page");
    	}
    	else
    	{
    		Log.info("counter is not started from the specified time period in the home page");
    	}
    	 
    	 
    	driver.quit();
    	
    }

    @Then("^check if the time lapse is matching witht the seconds timer$")
    public void check_if_the_time_lapse_is_matching_witht_the_seconds_timer() throws Throwable {
    	CounterPage cp=new CounterPage(driver);
    	
        if(cp.StartTimer1().isDisplayed())
        {
     	   Log.info("user is navigated to the counter page now");
        }
    	
   /* Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(16))
    		.pollingEvery(Duration.ofSeconds(1))
    		.ignoring(NoSuchElementException.class);
    WebElement foo = wait.until(new Function<WebDriver,WebElement>()
    {    
        public WebElement apply(WebDriver driver) 
        {    
        	String e=prop.getProperty("Time");
        	if(cp.StartTimer1().getText().equalsIgnoreCase(e))
        	{
        		Log.info(e);
        	}
        	else 
        		return null;

        }

    });*/
    	String e=prop.getProperty("Time");
    	int num=Integer.parseInt(e.replaceAll("\\D", ""));
    	
    	Log.info("Counter started from "+ num +" Seconds");
    	int count=0;
    	for(int i=0;i<num-1;i++)
    	{
    		String time=cp.StartTimer1().getText();
    		Thread.sleep(1000);
    		Log.info(time);
    		count++;
    	}
    	if(count==num)
    	{
    		Log.info("counter is in sync with seconds timer");
    	}
    	else
    	{
    		Log.info("Counter is not in sync with the seconds timer");
    	}
    	
    	String msg=driver.switchTo().alert().getText();
    	Log.info(msg);
    	driver.switchTo().alert().accept();
    	driver.switchTo().defaultContent();
    	driver.quit();
    		
    }

}
