package control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import session.Session;

public class Control {
    protected WebElement control;
    protected By locator;

    public Control(By locator){
        this.locator=locator;
    }

    protected void find(){
        control= Session.getInstance().getBrowser().findElement(this.locator);
    }

    public void click(){
        this.find();
        control.click();
    }
}