ant clean
ant build
adb push ./bin/UiAutoTest.jar /data/local/tmp

sleep 2

# run test by command
# adb shell uiautomator runtest UiAutoTest.jar -c com.lovexiaov.uiautotest.BaseUiTest -e test hello -e test2 world
