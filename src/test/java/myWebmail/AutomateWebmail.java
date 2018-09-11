package myWebmail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutomateWebmail {

	private static WebDriver driver;

	static Properties prop = new Properties();
	static String Username;
	static String Password;

	@BeforeClass
	public static void beforeClass() throws FileNotFoundException {
		driver = new ChromeDriver();
		driver.get("https://webmail.qainfotech.com:8443/#1");
		//driver.manage().window().maximize();

		FileInputStream fileInput = new FileInputStream("src/main/resources/dataFile.properties");
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Username = prop.getProperty("username");
		Password = prop.getProperty("password");
	}

	@Test (priority = 1)
	public void enterCredentials() {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(Username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		driver.navigate().to("https://webmail.qainfotech.com:8443/#1");
		Assert.assertEquals("https://webmail.qainfotech.com:8443/#1", driver.getCurrentUrl(),
				"Strings are not matching");
	}

	@AfterClass
	public static void afterClass() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
