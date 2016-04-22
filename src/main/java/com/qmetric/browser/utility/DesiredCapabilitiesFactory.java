package com.qmetric.browser.utility;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 06/03/2014
 */
public class DesiredCapabilitiesFactory {

    public static DesiredCapabilities create(Browser browser) throws Exception {
        if (browser.equals(Browser.CHROME)) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            return capabilities;
        } else if (browser.equals(Browser.FIREFOX)) {
            FirefoxProfile firefoxprofile = new FirefoxProfile();
            firefoxprofile.setAssumeUntrustedCertificateIssuer(false);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxDriver.PROFILE, firefoxprofile);
            return capabilities;
        } else if (browser.equals(Browser.INTERNET_EXPLORER)) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability("ignoreProtectedModeSettings", true);
            capabilities.setPlatform(Platform.ANY);
//            capabilities.setCapability("enablePersistentHover", false);
//            capabilities.setCapability("nativeEvents", false);
//            capabilities.setCapability("requireWindowFocus", true);
            return capabilities;
        } else {
            throw new Exception("Browser is not known");
        }
    }
}
