package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonActions {

	public Properties readDataPropertyFile() throws IOException{
		//FileInputStream file = new FileInputStream("/home/saumi/SAUMI/MyWork/WORKSPACE_CLASSES/OrangeHRM/Data.properties");
		FileInputStream file = new FileInputStream("Data.properties");
		Properties pro = new Properties();
		pro.load(file);
		return pro;
	}

	public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            //Move image file to new destination
                File DestFile=new File(fileWithPath);
                //Copy file at destination
                FileUtils.copyFile(SrcFile, DestFile);
    }
}
