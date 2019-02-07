package com.bestwehotel.app.appiumdemo;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import static com.bestwehotel.app.appiumdemo.CtripHotelSelect.selectBrandTab;

public class CtripCitySelect {
    public static void main(String[] args){
        AndroidDriver driver = getDriver();
        if(driver!=null){
            goHotelHomePage(driver);
            while (true) {
                goCityPage(driver);
                //scrollCityList(driver);
                clickCityIndexA(driver);
                clickNextCity(driver);

                CtripHotelSelect.goHotelList(driver);
                if(selectBrandTab(driver)){
                    while(!CtripHotelSelect.isScrollBottom(driver)){
                        CtripHotelSelect.scrollDownBrandList(driver);
                    }
                }else{

                }


                sleep();
            }
            //scrollCityList(driver);
        }
    }

    static AndroidDriver driver=null;
    public static AndroidDriver getDriver(){

        if(driver==null) {
            DesiredCapabilities des = new DesiredCapabilities();
            //    des.setCapability("automationName", "Appium");//Selendroid //自动化的模式选择
            //     des.setCapability("app", "C:\\software\\CalcTest.apk");//配置待测试的apk的路径
//      des.setCapability("browserName", "chrome");  //h5
            des.setCapability("platformName", "Android");//平台名称
            //des.setCapability("platformVersion", "4.4");//手机操作系统版本
            //des.setCapability("udid", "192.168.229.101:5555");//连接的物理设备的唯一设备标识
            des.setCapability("deviceName", "DWH9X17324W22072");//使用的手机类型或模拟器类型  UDID

            des.setCapability("appPackage", "ctrip.android.view");//App安装后的包名,注意与原来的CalcTest.apk不一样
            des.setCapability("appActivity", "ctrip.business.splash.CtripSplashActivity");//app测试人员常常要获取activity，进行相关测试,后续会讲到

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
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
        return driver;

    }

    private static void goHotelHomePage(AndroidDriver driver){
        MobileElement el1 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementById("ctrip.android.publicproduct:id/new_function_enter_home");
        el4.click();

        sleep();

        try {
            MobileElement cancelUpgrade = (MobileElement)driver.findElementById("ctrip.android.publicproduct:id/upgrade_cancel");
            if(cancelUpgrade!=null){
                cancelUpgrade.click();
            }
        }catch (Exception ex1){
            ex1.printStackTrace();
        }


        MobileElement el5 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout");
        el5.click();

        sleep();


    }

    private static void sleep(){
        try {
            Thread.sleep(5000);
        }catch (Exception ex1){
            ex1.printStackTrace();
        }
    }

    private static void goCityPage(AndroidDriver driver){
        MobileElement el1 = (MobileElement) driver.findElementById("ctrip.android.hotel:id/city_layout");
        el1.click();
    }


    private static void scrollCityList(AndroidDriver driver){
        sleep();
        (new TouchAction(driver)).press(PointOption.point(446,521)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
  .moveTo(PointOption.point(446,408)).release().perform();
    }

    private static boolean isExistHistory(AndroidDriver driver){
//        try {
//            MobileElement history = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView");
//            if("历史选择".equals(history.getText()){
//                return true;
//            }
//        }catch (Exception ex1){
//            ex1.printStackTrace();
//        }

        MobileElement indexList = (MobileElement) driver.findElementById("ctrip.android.view:id/list_view_index");
        List<MobileElement> text2 = indexList.findElementsByXPath("//android.widget.TextView[2]");
        ///hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[3]
        if(text2!=null&&text2.size()>0) {
            if ("历史".equals(text2.get(0).getText())) {
                return true;
            }
        }

        return false;
    }

    private static void clickCityIndexA(AndroidDriver driver){
        int indexA = 3;
        if(isExistHistory(driver)){
            indexA=4;
        }
        MobileElement indexList = (MobileElement) driver.findElementById("ctrip.android.view:id/list_view_index");
        List<MobileElement> aButton =  indexList.findElementsByXPath("//android.widget.TextView["+indexA+"]");
        if(aButton!=null&&aButton.size()>0) {
            aButton.get(0).click();
        }
    }

    //static String currentCity="";

    static List<String> selectedCity = new ArrayList<>();

    private static void clickNextCity(AndroidDriver driver){
        MobileElement cityList = getCityList(driver);

        List<MobileElement> cityInfo = cityList.findElementsByXPath("//android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");
        for(MobileElement element : cityInfo){
            if(!hasSelectedCity(element.getText())){
                element.click();
                return;
            }
        }
        scrollCityList(driver);
        clickNextCity(driver);
    }

    private static boolean hasSelectedCity(String cityName){
        if(!selectedCity.contains(cityName)){
            selectedCity.add(cityName);
            return false;
        }else{
            return true;
        }
    }

    private static MobileElement getCityList(AndroidDriver driver){
        return (MobileElement) driver.findElementById("ctrip.android.view:id/list_view");
    }

}
