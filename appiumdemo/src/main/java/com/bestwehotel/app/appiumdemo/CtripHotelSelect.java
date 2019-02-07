package com.bestwehotel.app.appiumdemo;

import java.time.Duration;
import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CtripHotelSelect {

    public static void main(String[] args){
        //AndroidDriver driver = CtripCitySelect.getDriver();

    }

    public static void goHotelList(AndroidDriver driver){
        try {
            MobileElement btn = (MobileElement) driver.findElementById("ctrip.android.hotel:id/btn_inquire");
            btn.click();
        }catch (Exception ex1){
            ex1.printStackTrace();
        }
    }

    public static boolean selectBrandTab(AndroidDriver driver){
        try {
            MobileElement filterBtn = (MobileElement) driver.findElementById("ctrip.android.hotel:id/all_list_sort_button_top_bar_layout");
            List<MobileElement> list = filterBtn.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView");
            if(list!=null&&list.size()>0){
                for(MobileElement element:list){
                    if("筛选".equals(element.getText())){
                        element.click();
                        break;
                    }
                }
            }

            try {
                Thread.sleep(3000);
            }catch (Exception ex1){
                ex1.printStackTrace();
            }


            MobileElement tabParent = (MobileElement) driver.findElementById("ctrip.android.hotel:id/tabs");

            List<MobileElement> tabList = tabParent.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView");

            for(MobileElement element:tabList){
                if("品牌".equals(element.getText())){
                    element.click();
                    return true;
                    //break;
                }
            }


        }catch (Exception ex1){
            ex1.printStackTrace();
        }
        return false;
    }

    public static boolean isScrollBottom(AndroidDriver driver){
        MobileElement groups = (MobileElement) driver.findElementById("ctrip.android.hotel:id/groups");
        try {
            List<MobileElement> title = groups.findElementsByXPath("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
            if(title!=null&&title.size()>0){
                for(MobileElement tit:title){
                    if("携程服务".equals(tit.getText())){
                        return true;
                    }
                }
            }
        }catch (Exception ex1){
            ex1.printStackTrace();
        }
        return false;
    }

    public static void expandBrand(AndroidDriver driver){
        MobileElement groups = (MobileElement) driver.findElementById("ctrip.android.hotel:id/groups");
        List<MobileElement> list = groups.findElementsByXPath("//android.widget.LinearLayout");

        for(MobileElement brandSection:list){
            List<MobileElement> brandTextList = brandSection.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView");
            if(brandTextList!=null&&brandTextList.size()>0){
                if("展开".equals(brandTextList.get(0).getText())){
                    brandTextList.get(0).click();

                    List<MobileElement> brandList = brandSection.findElementsByXPath("//android.support.v7.widget.RecyclerView");

                    if(brandList!=null&&brandList.size()>0){
                        List<MobileElement> brandTXT = brandList.get(0).findElementsByXPath("//android.widget.RelativeLayout/android.widget.TextView");
                        List<MobileElement> brandBG = brandList.get(0).findElementsByXPath("//android.widget.RelativeLayout/android.widget.ImageView");
                        if(brandBG!=null&&brandBG.size()>0){

                        }else{
                            if(brandTXT!=null&&brandTXT.size()>0){
                                if(isWehotelBrand(brandTXT.get(0).getText())){
                                    brandTXT.get(0).click();
                                }
                            }
                        }
                    }

                }
            }
        }

    }

    private static boolean isWehotelBrand(String brand){
        return true;
    }

    public static void scrollDownBrandList(AndroidDriver driver){
        (new TouchAction(driver))
                .press(PointOption.point(470,658)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(470,610)).release()
                .perform();
    }


    public static void btn_sumbit(AndroidDriver driver){
        MobileElement btn = (MobileElement)driver.findElementById("ctrip.android.hotel:id/btn_submit");
        btn.click();
    }

}
