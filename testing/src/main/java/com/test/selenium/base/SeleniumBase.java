package com.test.selenium.base;

import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumBase {
	
	private final static Logger LOGGER = Logger.getLogger(SeleniumBase.class.getName());
	protected WebDriver driver;

	public SeleniumBase() {
		LOGGER.fine("Initializing.");
		Properties prop = new Properties();
		try {
			prop.load( SeleniumBase.class.getClassLoader().getResourceAsStream("config.properties"));
			String browser = prop.getProperty("browser");
			LOGGER.info("Browser, " + browser);
			switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
				this.driver = new ChromeDriver();
				break;
	
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
				this.driver = new FirefoxDriver();
				break;
	
			default:
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.severe("Error initializing.");
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void googleSearch(String searchString) {
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys(searchString);
		WebElement submitButton =  driver.findElement(By.xpath("//input[@value='Google Search']"));
		submitButton.click();	
	}
	
	public String navigateGoogleLink(int nthLink) {
		String title;
		
		WebElement linkElement = driver.findElements(By.xpath("//h3[@class='LC20lb']")).get(nthLink);
		linkElement.click();
		title = driver.getTitle();
		
		return title;
	}
	
	public void cleanUpDriver() {
		driver.close();
	}
}
