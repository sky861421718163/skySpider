package com.sky.spider.executor;

import static org.junit.Assert.fail;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UntitledTestCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","d:/chromedriver.exe");//这一步必不可少  
		  
		driver = new ChromeDriver(); 

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("http://index.baidu.com/#/?login=1&fromu=http%3A%2F%2Findex.baidu.com%2F%3Ftpl%3Dtrend%26word%3D%25C8%25E7%25BC%25D2");
	  
    driver.findElement(By.id("TANGRAM__PSP_4__userName")).click();
    driver.findElement(By.id("TANGRAM__PSP_4__userName")).clear();
    driver.findElement(By.id("TANGRAM__PSP_4__userName")).sendKeys("861421718@qq.com");
    
    driver.findElement(By.id("TANGRAM__PSP_4__password")).click();
    driver.findElement(By.id("TANGRAM__PSP_4__password")).clear();
    driver.findElement(By.id("TANGRAM__PSP_4__password")).sendKeys("sky11262611iu");
    driver.findElement(By.id("TANGRAM__PSP_4__submit")).click();
    driver.findElement(By.id("schword")).click();
    driver.findElement(By.id("schsubmit")).click();
    
    driver.get("http://index.baidu.com/?tpl=trend&word=%CF%A3%B6%FB%B6%D9");
    
    driver.findElement(By.xpath("//div[@id='auto_gsid_4']/a[2]/div")).click();
    
   // waitForElementVisible(driver, By.xpath("//*[@id='auto_gsid_5']/div[2]/table/tbody/tr[2]/td[2]/div"), 10);
    
    WebElement  a = driver.findElement(By.xpath("//*[@id='auto_gsid_5']/div[2]/table/tbody/tr[2]/td[2]/div/span[1]"));
    File screenshot= captureElement(a );

    
    //File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//-------------------------截屏操作        
    FileUtils.copyFile(screenshot, new File("C:\\Users\\Administrator\\Desktop\\screenshots1.jpg"));//----------保存图片
  }

  @After
  public void tearDown() throws Exception {
   // driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  
  public static File captureElement(WebElement element) throws Exception {
      // TODO Auto-generated method stub
      WrapsDriver wrapsDriver = (WrapsDriver) element;
      // 截图整个页面
      File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
      BufferedImage img = ImageIO.read(screen);
      // 获得元素的高度和宽度
      int width = element.getSize().getWidth();
      int height = element.getSize().getHeight();
      // 创建一个矩形使用上面的高度，和宽度
      Rectangle rect = new Rectangle(width, height);
      // 得到元素的坐标
      Point p = element.getLocation();
      BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
      // 存为png格式
      ImageIO.write(dest, "png", screen);
      return screen;
  }

  
	/**
	 * 等待页面加载的条件
	 */
	private static ExpectedCondition<Boolean> pageLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return
			((JavascriptExecutor)driver)
			.executeScript("return document.readyState;")
			.equals("complete");
		}
	};
	
	public static void waitForElementVisible(WebDriver webdriver, By by, long timeout) {
		WebDriverWait wait = new WebDriverWait(webdriver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

}
