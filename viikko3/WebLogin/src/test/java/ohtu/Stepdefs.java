package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import ohtu.domain.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentCredentialsAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndPasswordConfirmationAreGiven(String username, String password){
        signUpWith(username, password, password);
    }

    @Then("a new user is created")
    public void newUserIsCreated() { pageHasContent("Welcome to Ohtu Application!"); }

    @When("a too short username {string} and password {string} and matching password confirmation are entered")
    public void tooShortUsernameAndValidPasswordAreGiven(String username, String password){
        signUpWith(username, password, password);
    }

    @When("a valid username {string} and too short password {string} and matching password confirmation are entered")
    public void validUsernameAndTooShortPasswordAreGiven(String username, String password){
        signUpWith(username, password, password);
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsGiven(String error){
        pageHasContent(error);
    }

    @When("a valid username {string} and password {string} and non-matching password confirmation {string} are entered")
    public void nonMatchingPasswordConfirmationIsGiven(String username, String password, String pwConfirmation){
        signUpWith(username, password, pwConfirmation);
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void newUserIsCreatedSuccessfully(String username, String password){
        signUpAndLogout(username, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithInvalidCredentialsIsTriedToBeCreated(String username, String password){
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        signUpWith(username, password, password);
    }

    @When("attempting to login with nonexistent username {string} and password {string}")
    public void loginAttemptWithNonExistentUser(String username, String password){
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        logInWith(username, password);

    }

    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    private void signUpWith(String username, String password, String pwConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pwConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void signUpAndLogout(String username, String password){
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        assertTrue(driver.getPageSource().contains("Create username and give password"));
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();

        assertTrue(driver.getPageSource().contains("Welcome to Ohtu Application!"));
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        assertTrue(driver.getPageSource().contains("Ohtu Application main page"));
        element = driver.findElement(By.linkText("logout"));
        element.click();

    }
}
