package com.bestwehotel.app.appiumdemo;//package com.androidstudy.androidjetpackdemo.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class Demo {
    public static void main(String[] args){
        AndroidDriver driver;
        DesiredCapabilities des = new DesiredCapabilities();
        //    des.setCapability("automationName", "Appium");//Selendroid //自动化的模式选择
        //     des.setCapability("app", "C:\\software\\CalcTest.apk");//配置待测试的apk的路径
//      des.setCapability("browserName", "chrome");  //h5
        des.setCapability("platformName", "Android");//平台名称
        //des.setCapability("platformVersion", "4.4");//手机操作系统版本
        //des.setCapability("udid", "192.168.229.101:5555");//连接的物理设备的唯一设备标识
        des.setCapability("deviceName", "emulator-5554");//使用的手机类型或模拟器类型  UDID

        des.setCapability("appPackage", "com.androidstudy.androidjetpackdemo");//App安装后的包名,注意与原来的CalcTest.apk不一样
        des.setCapability("appActivity", ".ui.ui.MainActivity");//app测试人员常常要获取activity，进行相关测试,后续会讲到

        //appium服务端超过设置的时间没有收到消息时认为客户端退出，默认60
        des.setCapability("newCommandTimeout", 60);
        //等待测试设备ready的超时时间
        des.setCapability("devicereadyTimeout", 30);
        //是否启用支持unicode的键盘
        des.setCapability("unicodeKeyboard", true);
        //session结束后是否重置键盘
        des.setCapability("resetKeyboard", true);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), des);//虚拟机默认地址
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//设置超时等待时间,默认250ms
            //driver.
            MobileElement el1 = (MobileElement) driver.findElementById("com.androidstudy.androidjetpackdemo:id/firstName");
            el1.sendKeys("123321");
            //el1.execute()
            MobileElement el2 = (MobileElement) driver.findElementById("com.androidstudy.androidjetpackdemo:id/lastName");
            el2.sendKeys("dingbingb");
            MobileElement el3 = (MobileElement) driver.findElementById("com.androidstudy.androidjetpackdemo:id/age");
            el3.sendKeys("dingbingb888");
            MobileElement el4 = (MobileElement) driver.findElementById("com.androidstudy.androidjetpackdemo:id/saveUser");
            el4.click();

            for(int i=0;i<10;i++) {
                el4.click();
                el4.click();
                el4.click();
                el4.click();
            }

            new TouchAction(driver).press(PointOption.point(150, 1569)).waitAction().moveTo(PointOption.point(150, 1079)).release().perform();

            //new TouchAction(driver).

        }catch (Exception ex1){
            ex1.printStackTrace();
        }




//        driver.findElement(By.id("com.android.calculator2:id/digit1")).click();//定位'1'
//        driver.findElement(By.id("com.android.calculator2:id/plus")).click();//定位'+'
//        driver.findElement(By.id("com.android.calculator2:id/digit6")).click();//定位'6'
//        driver.findElement(By.id("com.android.calculator2:id/equal")).click();//定位'='
    }
}
