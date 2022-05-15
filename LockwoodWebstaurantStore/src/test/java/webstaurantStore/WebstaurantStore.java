/**
 * 
 */
package webstaurantStore;

import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * @author Evelyn
 *
 */

public class WebstaurantStore {
	
	@Test
	public void tableTest() throws Exception {
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.webstaurantstore.com/");
		
		WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"searchval\"]"));
		searchBox.sendKeys("stainless work table" + Keys.ENTER);
		
		for (int x = 0; x < 1;)
		{
			validatePageResults(driver);
			
			WebElement nextPage = driver.findElement(By.cssSelector(".rc-pagination-next"));
			
			if(nextPage.isEnabled()) {
				nextPage.click();
			}
			else {
				x++;
			}
		}
		
		driver.quit();
	}
	
	public void validatePageResults(WebDriver driver) throws Exception {
		List<WebElement> searchResults = driver.findElements(By.cssSelector("a[data-testid='itemDescription']"));
		
		for (WebElement result : searchResults) {
			String resultText = result.getText();
			
            if((resultText.matches("(.*)Table(.*)"))){
            	System.out.println(result.getText() + ": Success");
            }
            else{
            	throw new Exception(result.getText() + ": result does not contain table");
            }	
		}
	}
}
