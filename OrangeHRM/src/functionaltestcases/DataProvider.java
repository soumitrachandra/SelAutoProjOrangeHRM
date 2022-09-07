package functionaltestcases;

import java.io.IOException;
import java.util.Properties;

import utility.CommonActions;

public class DataProvider {

@org.testng.annotations.DataProvider(name="getLoginData")
	public Object[][] getDataForLogin() throws IOException {
		/*return new Object[][] {
				{"Saumi","Software Testing"},
				{"sachin","Devloper"},
				{"Sagar","Service"}
		};*/
	Object[][] arr = new Object[2][2];
	CommonActions com = new CommonActions();
	Properties pro = com.readDataPropertyFile();
	arr[0][0]=pro.getProperty("UserName_InCorrect");
	arr[0][1]=pro.getProperty("Pwd_InCorrect");
	arr[1][0]="";
	arr[1][1]="";
	return arr;
	}
}
