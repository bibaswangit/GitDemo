

package qaclickacademy.Mavenjava;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

@Test
public class mp3SongDownload{
	
	public void mp3download() 
	{
		
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	System.setProperty("webdriver.chrome.driver", "D:\\Work\\chromedriver.exe");
	WebDriver driver = new ChromeDriver(options);
	WebDriverWait w= new WebDriverWait(driver,20);
	driver.manage().window().maximize();
	driver.get("https://gaana.com/artist/rabindra-sangeet");
	WebElement se= driver.findElement(By.xpath("//div[@class='content-container artistDetailPage artistSongs']"));
	String song_names=driver.findElement(By.xpath("//div[@class='content-container artistDetailPage artistSongs']")).getText();
	System.out.println(song_names);
	int len= se.findElements(By.xpath("//div/a[@data-type='playSong']")).size();
	System.out.println(len);
	List<WebElement> Elements =se.findElements(By.xpath("//div/a[@data-type='playSong']"));
//	List<WebElement> Elements =se.findElements(By.xpath("//div[@data-type='playSong' and @class='playlist_thumb_det']"));
	
	String clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
	for (WebElement ele : Elements)
	{
		String sl = ele.getText();
		String s = ele.getAttribute("href");
		driver.findElement(By.xpath("//a[@class='logo-link desktop']")).sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		Iterator<String> windws = driver.getWindowHandles().iterator();
		String pw = windws.next();
		String cw = windws.next();
		driver.switchTo().window(cw);
		driver.get("https://www.youtube.com/results?search_query="+sl);
		String link = driver.findElements(By.xpath("//a[@class='yt-simple-endpoint style-scope ytd-video-renderer']")).get(0).getAttribute("href");
		driver.get("https://ytmp3.cc/youtube-to-mp3/");
		driver.findElement(By.id("input")).sendKeys("https://www.youtube.com/"+link);
		driver.findElement(By.id("submit")).click();
		try {
			w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Download']")))).click();
		}
		catch (NoSuchContextException e) {
			
		}
		driver.close();
		driver.switchTo().window(pw);

	}
	
	driver.close();
	}

}
