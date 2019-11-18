package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());

/*      //////LOGIN WITH VALID CREDENTIALS
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        element.submit();

        System.out.println(driver.getPageSource());*/


/*      //////LOGIN WITH INVALID PASSWORD
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("incorrect");
        element = driver.findElement(By.name("login"));

        element.submit();

        System.out.println(driver.getPageSource());*/


/*      //////SIGNUP WITH VALID CREDENTIALS
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys(randomName());
        element = driver.findElement(By.name("password"));
        element.sendKeys("passw0rd");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("passw0rd");

        element.submit();

        System.out.println(driver.getPageSource());
        
        driver.quit();*/

        //////SIGNUP AND LOGOUT
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys(randomName());
        element = driver.findElement(By.name("password"));
        element.sendKeys("passw0rd");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("passw0rd");

        element.submit();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println(driver.getPageSource());

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static String randomName(){
        Random r = new Random();
        return "pasi" + r.nextInt(100000);
    }
}
