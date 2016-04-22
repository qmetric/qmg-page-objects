package com.qmetric.browser.utility;

import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 03/03/2014
 */
public class WebDriverSingleton {

    private static RemoteWebDriver driver;

    public static RemoteWebDriver createInstance(RemoteDriverProperties properties)
            throws Exception {

        if (driver == null)
        {
            DesiredCapabilities capabilities = DesiredCapabilitiesFactory.create(Browser.getBrowserBy(properties.getBrowser()));

            driver = (RemoteWebDriver) new Augmenter()
                    .augment(new RemoteWebDriver(new URL("http://" + properties.getHubHost() + ":" + properties.getHubPort() + "/wd/hub"), capabilities));
            driver.setFileDetector(new LocalFileDetector());
            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        }
        return driver;
    }

    public static RemoteWebDriver getInstance()
    {
        return driver;
    }

    public static void resetDriver()
    {
        driver = null;
    }

}
