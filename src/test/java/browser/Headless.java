package browser;

import org.openqa.selenium.WebDriver;

public class Headless implements IBrowser{
    @Override
    public WebDriver create() {
        return null;
    }
}