package com.qmetric.browser.utility;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 06/03/2014
 */
public class RemoteDriverProperties {

    private String hubHost;
    private String hubPort;
    private String browser;

    public String getHubHost() {
        return hubHost;
    }

    public void setHubHost(String hubHost) {
        this.hubHost = hubHost;
    }

    public String getHubPort() {
        return hubPort;
    }

    public void setHubPort(String hubPort) {
        this.hubPort = hubPort;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
