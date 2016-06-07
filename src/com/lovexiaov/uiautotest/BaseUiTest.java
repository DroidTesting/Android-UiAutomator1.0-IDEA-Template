package com.lovexiaov.uiautotest;

import android.util.Base64;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import java.util.Arrays;


public class BaseUiTest extends UiAutomatorTestCase {

    public void testEnvIsOk() throws UiObjectNotFoundException {
        UiDevice.getInstance().pressHome();

//        Bundle params = getParams();
//        String test = params.getString("test2");
//        System.out.println("the param is: " + test);
//        new UiObject(new UiSelector().text("")).setText("");
//        sleep(5);
//
//        try {
//            Runtime.getRuntime().exec("am start com.example.audioplayer/.AudioActivity");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

//        String prog = "am start -n com.example.audioplayer/.AudioActivity";
//        try {
//            Process process = Runtime.getRuntime().exec(prog);
//            process.waitFor();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        boolean b = getUiDevice().pressHome();
        System.out.println("========" + b);
//        System.out.println(Arrays.toString(Base64.decode("5bCP5pm6KDAwMUZFRik=", Base64.DEFAULT)));
    }
}