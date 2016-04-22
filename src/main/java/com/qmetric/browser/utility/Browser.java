package com.qmetric.browser.utility;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    INTERNET_EXPLORER("internet_explorer");

    private String browserId;

    Browser(String browserId) {
        this.browserId = browserId;
    }

    public static Browser getBrowserBy(String browserId) {
        for (Browser browser : Browser.values()) {
            if (browserId.equals(browser.getBrowserId())) {
                return browser;
            }
        }
        throw new IllegalArgumentException("Unknown browser with id " + browserId);
    }

    public String getBrowserId() {
        return browserId;
    }
}
