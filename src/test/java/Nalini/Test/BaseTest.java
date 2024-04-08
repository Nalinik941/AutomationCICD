package Nalini.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Nalini.Pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage LP;
	
	public WebDriver initializerDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Nalini//resources//GlobalData.Properties");
		prop.load(fis);
		
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//String browsername=prop.getProperty("browser");(when we are fetching data from datareader file
		
	//	if(browsername.equalsIgnoreCase("Chrome"))
		if(browsername.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browsername.contains("headless")) {
		options.addArguments("headless");
		}
		 driver =new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));//Full screen
		}
		else if(browsername.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C://Users//Dell//Documents//geckodriver.exe");
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
}
	
	public List<HashMap<String, String>> getjsondatatomap(String FilePath) throws IOException{
		String jsoncontent=FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
	}
	
	public String getscreenshot(String testcasename,WebDriver driver) throws IOException{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return (System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
		driver=initializerDriver();
		LP=new LandingPage(driver);
		LP.Goto();
		return LP;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.close();
	}
}
