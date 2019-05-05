package com.sky.spider.executor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.WrapsDriver;

import com.baidu.aip.ocr.AipOcr;



public class BaiduIndexCollecter2 {
	
	private static ArrayList<String> iList =new ArrayList<String>();
	 //设置APPID/AK/SK
    public static final String APP_ID = "11295047";
    public static final String API_KEY = "y4i82xRWEe4zNejw47L2eZ0d";
    public static final String SECRET_KEY = "AfW0TXPtD70g68DHBftwp86S9iTDSauP";
    public static final AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

	public static void main(String[] args) throws UnsupportedEncodingException {
		

		System.setProperty("webdriver.chrome.driver", "d:/chromedriver.exe");// 这一步必不可少
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		

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
		nList.add("喜来登");
		nList.add("洲际");
		nList.add("香格里拉");
		nList.add("皇冠假日");
		nList.add("万豪");
		nList.add("四季");
		nList.add("凯悦");
		nList.add("君悦");
		nList.add("铂尔曼");
		nList.add("索菲特");
		
		for(String name : nList){
			
			String dz = URLEncoder.encode(name, "gb2312");

			driver.get("http://index.baidu.com/?tpl=trend&word="+dz);

			driver.findElement(By.xpath("//div[@id='auto_gsid_4']/a[2]/div")).click();

		
			captureElement(driver,name);
			
		}
		
		
		iList.forEach(e ->{
			System.out.println(e);
		});

	}

	public static boolean captureElement(WebDriver driver,String name)   {
		
			//下面可能出现找不到元素 ，记录错误1
		WebElement indexElement = null;
		try {
			indexElement = driver.findElement(By.xpath("//div[@id='auto_gsid_5']/div[3]/table/tbody/tr[2]/td[2]/div/span"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
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
			//下面可能截取到空白图片，记录错误2
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
	    File nf =  new File("C:\\Users\\Administrator\\Desktop\\screenshots2.png"); 
	
			ImageIO.write(grayImage, "png", nf);
			
			 ByteArrayOutputStream out = new ByteArrayOutputStream();  
			 ImageIO.write(grayImage, "png", out); 
			 byte[] b = out.toByteArray();  
			
			 HashMap<String, String> options = new HashMap<String, String>();
			    options.put("language_type", "ENG");
			    options.put("detect_direction", "true");
			    options.put("detect_language", "true");
			    options.put("probability", "true");
			 
			  //下面 如果截取到空白图片，识别结果为 空 ，记录错误2
			    JSONObject res = client.basicGeneral(b, options);
			    System.out.println(res.toString(2));	
			    JSONArray dataArr = res.getJSONArray("words_result");
			   String r = dataArr.getJSONObject(0).getString("words");

           // System.out.print(recognizeText + "\t");
            iList.add(name + ":" + r);
		
	}
	
	

}
