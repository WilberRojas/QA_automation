package basicSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.Thread;
import java.util.Date;
import org.openqa.selenium.Keys;

public class BasicSeleniumTest {

    WebDriver driver;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://todo.ly/");
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
    }

    @Test
    public void verifyCRUDProject() throws InterruptedException {
        // login
        login();

        // create
        String nameProject="Wil"+new Date().getTime();
        create_project(nameProject);

        String nameTask1="Take over the world";
        String nameTask2="Be happy";
        String nameTask3="Buy bread, milk, eggs";

        create_task(nameTask1);
        create_task(nameTask2);
        create_task(nameTask3);

        edit_task(nameTask1, nameTask1+" (changed)");
    }

    public void login() throws InterruptedException{
        driver.findElement(By.xpath("//img[contains(@src,'pagelogin')]")).click();
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("bootcamp@mojix44.com");
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.findElement(By.id("ctl00_HeaderTopControl1_LinkButtonLogout")).isDisplayed()
                ,"ERROR login was incorrect");
    }

    public void create_project(String nameProject) throws InterruptedException{
        driver.findElement(By.xpath("//td[text()='Add New Project']")).click();
        driver.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        driver.findElement(By.id("NewProjNameButton")).click();
        Thread.sleep(1000);
        int actualResult=driver.findElements(By.xpath(" //td[text()='"+nameProject+"'] ")).size();
        Assertions.assertTrue(actualResult >= 1
                ,"ERROR The project was not created");
    }

    public void create_task(String nameTask) throws InterruptedException{
        driver.findElement(By.id("NewItemContentInput")).sendKeys(nameTask);
        driver.findElement(By.id("NewItemAddButton")).click();
        Thread.sleep(1000);
    }

    public void edit_task(String nameTask, String updateTask) throws InterruptedException{
        WebElement variable1 = driver.findElement(By.xpath(" //div[text()='"+nameTask+"'] "));
        String id1 = variable1.getAttribute("itemid");

        driver.findElement(By.xpath(" //td/div[@itemid='"+id1+"'] ")).click();
        driver.findElement(By.xpath(" //textarea[@itemid='"+id1+"']")).clear();
        driver.findElement(By.xpath(" //textarea[@itemid='"+id1+"']")).sendKeys(updateTask);
        driver.findElement(By.xpath(" //textarea[@itemid='"+id1+"']")).sendKeys(Keys.RETURN);
    }

}