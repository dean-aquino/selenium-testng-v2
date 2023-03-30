package test.Whiz.Login;

import base.BaseClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.Whizible_HomePage;
import pageObjects.Whizible_LoginPage;

@Listeners(utilities.TestNGListeners.class)
public class TC005_Login_NullUsernameAndPassword extends BaseClass {
    Whizible_LoginPage loginPage;
    Whizible_HomePage homePage;
    public static String sheetName = "Login"; // sheet name of the test data to be used
    public static String tcName = "Whizible_Login"; // test case name
    public static String username;
    public static String password;

    public void loginToWhizible(String username, String password){
        loginPage = new Whizible_LoginPage(getDriver());
        homePage = new Whizible_HomePage(getDriver());
        loginPage.setUsername(sheetName,tcName,username);
        loginPage.setPassword(sheetName,tcName,password);
        loginPage.clickLogin();
        if(username.toUpperCase().equals("VALID USERNAME") && password.toUpperCase().equals("VALID PASSWORD")){
            homePage.verifySuccessfulLogin();
        } else if(username.toUpperCase().equals("NULL") && password.toUpperCase().equals("NULL")){
            loginPage.verifyEmptyUsernameField();
            loginPage.verifyEmptyPasswordField();
        } else{
            loginPage.verifyLoginErrorMessage();
        }
    }

    @Test
    public void NullUsernameAndPassword() {
        username = "Null";
        password = "Null";
        loginToWhizible(username, password);
    }
}
