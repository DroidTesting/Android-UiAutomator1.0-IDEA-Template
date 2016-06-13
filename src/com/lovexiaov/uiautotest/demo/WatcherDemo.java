package com.lovexiaov.uiautotest.demo;

/**
 * 摘自文章: http://www.everybodytests.com/2012/11/uiautomator-and-watchers-adding-async.html
 * <p />
 *
 * 略作了一些修改~
 */


import android.util.Log;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class WatcherDemo extends UiAutomatorTestCase {

    private static final String LOG_TAG = "WatcherDemoEx1";
    private static final String MYOKCANCELDIALOGWATCHER_STRING = "OkCancelDialogWatcher";


    public void testWatcherDemoTestExample1() throws UiObjectNotFoundException {
        Log.w(LOG_TAG, "Starting our test!");

        // Simulate a short press on the HOME button based on the sample test case:
        //
        // http://developer.android.com/tools/testing/testing_ui.html#sample
        //
        getUiDevice().pressHome();

        // We're now on the home screen. Launch the All Apps screen.
        UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));

        // Simulate a click to bring up the All Apps screen.
        allAppsButton.clickAndWaitForNewWindow();

        // In the All Apps screen, the Snow Report app should be in the Apps tab
        // assuming it is installed prior to starting this test
//        UiObject appsTab = new UiObject(new UiSelector().text("Apps"));

        // Simulate a click to enter the Apps tab.
//        appsTab.click();

        // Next, in the apps tabs, we can simulate a user swiping until they
        // come across the Snow Report app icon. Again, swiped from the
        // sample test case.
        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

        // Set the swiping mode to horizontal (the default is vertical)
        // This is only compatible with API lvl 17. Short of that it will crash
        // with a "method not found" failure.
//        appViews.setAsHorizontalList();

        // Create a UiSelector to find the Snow Report app and simulate
        // a user click to launch the app.
//        UiObject apiDemoApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "API Demos");
        UiObject apiDemoApp = new UiObject(new UiSelector().text("API Demos"));
        apiDemoApp.clickAndWaitForNewWindow();

        /////////////////////////////////////////////////////////////////////
        // This concludes the section devoted to simply launching the app. //
        /////////////////////////////////////////////////////////////////////

        // Define watcher
        UiWatcher okCancelDialogWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                System.out.println("UiWatcher is invoked~~");
                UiObject okCancelDialog = new UiObject(new UiSelector().textStartsWith("Lorem ipsum"));
                if (okCancelDialog.exists()) {
                    Log.w(LOG_TAG, "Found the example OK/Cancel dialog");
                    UiObject okButton = new UiObject(new UiSelector().className("android.widget.Button").text("OK"));
                    try {
                        okButton.click();
                    } catch (UiObjectNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

//                    return (okCancelDialog.waitUntilGone(25000));
                }
                return false;
            }
        };

        // Register watcher
        UiDevice.getInstance().registerWatcher(MYOKCANCELDIALOGWATCHER_STRING, okCancelDialogWatcher);

        // Run watcher
        UiDevice.getInstance().runWatchers();


		/*
         * This test demonstrates UiAutomator Watchers using the Emulator's
		 * API Demos App/Alert Dialogs
		 *
		 * With this, the watcher will be set in advance to identify when
		 * an alert dialog is present and cancel it. The test will click
		 * on at least one item in the list.
		 */

        // Get Api Demos list
        UiCollection apiDemoList = new UiCollection(new UiSelector().className("android.widget.ListView"));

        sleep(2 * 1000);

        // Click on App
        System.out.println("Find App item~~");
        UiObject appTextView = apiDemoList.getChildByText(new UiSelector()
                        .className(android.widget.TextView.class.getName()),
                "App");

        sleep(2 * 1000);

        appTextView.clickAndWaitForNewWindow();

        // Get App demo list
        UiCollection appDemoList = new UiCollection(new UiSelector().className("android.widget.ListView"));
        sleep(2 * 1000);

        System.out.println("Find Alert Dialogs~~");
        // Click on Alert Dialogs
        UiObject alertDialogTextView = appDemoList.getChildByText(new UiSelector()
                .className(android.widget.TextView.class.getName()), "Alert Dialogs");
        sleep(2 * 1000);

        alertDialogTextView.clickAndWaitForNewWindow();

        System.out.println("Find Ok item 1~~");
        // Click on button with text "OK Cancel dialog with a message"
        UiObject okCancelDialogButton1 = new UiObject(new UiSelector().text("OK Cancel dialog with a message"));
        sleep(2 * 1000);
        okCancelDialogButton1.click();
        System.out.println("Click ok item 1~~");

        // Click on button with text "OK Cancel dialog with a long message"
        // This is where the watcher should save our bacon. Yes, this is a poorly written test case, I know.
        // Just go with me here, I'm making a point about Watchers. If the dialog from the previous button
        // press is still in place, this new selector will fail to find an object containing that text.
        // Because the watcher is there, the dialog gets closed and we're all good.

        System.out.println("Find Ok item 2~~");
        UiObject okCancelDialogButton2 = new UiObject(new UiSelector().text("OK Cancel dialog with a long message"));
        sleep(2 * 1000);
        okCancelDialogButton2.click();

    }

}