package session;

import browser.FactoryBrowser;
import org.openqa.selenium.WebDriver;

public class Session {
    private static Session instance=null;
    private WebDriver browser;
    private Session(){
        browser = FactoryBrowser.make("chrome").create();
    }

    public static Session getInstance(){
        if (instance==null)
            instance=new Session();
        return instance;
    }

    public void closeSession(){
        browser.quit();
        instance=null;
    }

    public WebDriver getBrowser() {
        return browser;
    }
}
