package stepDefinition;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {

	public static WebDriver driver;
	//Actualvalue actualvalue;

	@Given("User opens browser and browser gets loaded with the dynamic dropdown visible")
	public void user_opens_browser_and_browser_gets_loaded_with_the_dynamic_dropdown_visible() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
		driver.get("https://www.bstackdemo.com/");

	}

	@When("the user tries to select the option of his wish")
	public void the_user_tries_to_select_the_option_of_his_wish() {

		List<WebElement> options = driver.findElements(By.xpath("//p[@class='shelf-item__title']"));
		System.out.println(options.size());
		
		//WebElement option = driver.findElement(By.xpath("//p[contains(text(),'iPhone 12 Mini')]/..//div[contains(text(),'Add to cart')]"));
		
		for(int i=0;i<options.size();i++)
		{
			if(options.get(i).getText().equalsIgnoreCase("iPhone 12 Mini"))
			{
				WebElement addToCartButton=driver.findElement(By.xpath("//p[.='iPhone 12 Mini']/..//div[.='Add to cart']"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click()", addToCartButton);
				break;
			}
		}

	}

	@Then("the user verifies the same")
	public void the_user_verifies_the_same() {

		WebElement element = driver.findElement(By.xpath("//*[@class='sort']/Select"));

		Select sel = new Select(element);
		sel.selectByVisibleText("Highest to lowest");
		// System.out.println(sel.getOptions());

		List<WebElement> op = sel.getOptions();

		for (int i = 0; i < op.size(); i++) {
			String optionlist = op.get(i).getText();
			System.out.println(optionlist);
		}
		
		String actualvalue = sel.getFirstSelectedOption().getText();
		//System.out.println(actualvalue);
		
		String expectedvalue ="Highest to lowest";

		
		assertEquals(actualvalue, expectedvalue);
		System.out.println("Comparision sucessful");

	}
	@And("user closes the browser")
	public void user_closes_the_browser() {
	    
		driver.quit();
	}

}
