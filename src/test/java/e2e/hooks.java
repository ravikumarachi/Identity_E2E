package e2e;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import e2e.common.webdriver;

public class hooks {

    e2e.common.webdriver webdriver = new webdriver();
    String host;

    @Before
    public void open() {
        webdriver.openBrowser();
    }


    @After
    public void close() {
        webdriver.closeBrowser();
    }


}