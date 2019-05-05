package com.sky.spider.executor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaiduIndexCollecter {
	
	private static ArrayList<String> iList =new ArrayList<String>();

	public static void main(String[] args) throws UnsupportedEncodingException {

		System.setProperty("webdriver.chrome.driver", "d:/chromedriver.exe");// 这一步必不可少
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		/*String proxyIpAndPort= "124.152.32.140:53281";  
        DesiredCapabilities cap = new DesiredCapabilities();  
        Proxy proxy=new Proxy();  
        proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);  
        cap.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);  
        cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);  
        System.setProperty("http.nonProxyHosts", "localhost");  
        cap.setCapability(CapabilityType.PROXY, proxy); */

		driver.get("http://index.baidu.com/#/?login=1&fromu=http%3A%2F%2Findex.baidu.com%2F%3Ftpl%3Dtrend%26word%3D%25C8%25E7%25BC%25D2");

		driver.findElement(By.id("TANGRAM__PSP_4__userName")).click();
		driver.findElement(By.id("TANGRAM__PSP_4__userName")).clear();
		driver.findElement(By.id("TANGRAM__PSP_4__userName")).sendKeys("15660436959");

		driver.findElement(By.id("TANGRAM__PSP_4__password")).click();
		driver.findElement(By.id("TANGRAM__PSP_4__password")).clear();
		driver.findElement(By.id("TANGRAM__PSP_4__password")).sendKeys("awei1989");
		driver.findElement(By.id("TANGRAM__PSP_4__submit")).click();
		driver.findElement(By.id("schword")).click();
		driver.findElement(By.id("schsubmit")).click();
		
		List<String>  nList = new ArrayList<>();
		nList.add("如家");
		nList.add("速8");
		nList.add("希尔顿");
		nList.add("格林豪泰");
		for(String name : nList){
			
			String dz = URLEncoder.encode(name, "gb2312");

			driver.get("http://index.baidu.com/?tpl=trend&word="+dz);

			driver.findElement(By.xpath("//div[@id='auto_gsid_4']/a[2]/div")).click();

			boolean flag = false ;
			while (flag ==false){
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				flag =captureElement(driver,name);
			}
			
		}
		
		
		iList.forEach(e ->{
			System.out.println(e);
		});

	}

	public static boolean captureElement(WebDriver driver,String name)   {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement indexElement =  driver.findElement(By.xpath("//div[@id='auto_gsid_5']/div[3]/table/tbody/tr[2]/td[2]/div/span"));
		try {
			WrapsDriver wrapsDriver = (WrapsDriver) indexElement;
			// 截图整个页面
			File screenshot = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
			// FileUtils.copyFile(screenshot, new File("C:\\Users\\Administrator\\Desktop\\bdindx\\screenshots0.png"));
			BufferedImage img = ImageIO.read(screenshot);
			// 获得元素的高度和宽度
			int width = indexElement.getSize().getWidth();
			int height = indexElement.getSize().getHeight();
			// 创建一个矩形使用上面的高度，和宽度
			Rectangle rect = new Rectangle(width, height);
			// 得到元素的坐标
			Point p = indexElement.getLocation();
			BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
			// 存为png格式
			ImageIO.write(dest, "png", screenshot);
		    FileUtils.copyFile(screenshot, new File("C:\\Users\\Administrator\\Desktop\\bdindx\\"+name+".png"));// ----------保存图片
		    
		   trans(dest,name);
		   // driver.quit(); 
		   // driver.close();
	      /* String text = HanmingOCR.doHanmingOCR(dest);
			
			System.out.println("r="+text);
*/
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	
	public static void trans(BufferedImage preImg,String name) throws IOException   {
		
		int width = preImg.getWidth();  
	    int height = preImg.getHeight();  
	    
	    BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY  
	    for(int i= 0 ; i < width ; i++){  
	        for(int j = 0 ; j < height; j++){  
	        int rgb = preImg.getRGB(i, j);  
	        grayImage.setRGB(i, j, rgb);  
	        }  
	    }  
	    File nf =  new File("C:\\Users\\Administrator\\Desktop\\screenshots2.jpg"); 
	
			ImageIO.write(grayImage, "png", nf);
			
			
            String recognizeText = null;
			try {
				recognizeText = new OCRHelper().recognizeText(nf);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.print(recognizeText + "\t");
            iList.add(name + ":" + recognizeText);
		
	}
	
	

}
