package com.lovexiaov.uiautotest.demo;

import android.os.Bundle;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * Created by lovexiaov on 16/6/13.
 */
public class CommonDemo extends UiAutomatorTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        receivedParamsFromCMD();
    }

    private void receivedParamsFromCMD() {
        Bundle params = getParams();
        String p1 = params.getString("p1");
        System.out.println("The param p1's value is: " + p1);
    }

}
