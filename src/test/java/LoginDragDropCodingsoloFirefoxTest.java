import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

import static org.junit.Assert.assertTrue;

public class LoginDragDropCodingsoloFirefoxTest {
    WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("Initalsiere Webdriver");
        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // hej driver, maximiere das Fenster!
        driver.get("https://seleniumkurs.codingsolo.de");
    }


    @Test
    public void testLogin() {
        System.out.println("Test gestartet!");

        // Arrange - zuerst under Eingabefelder befüllen
        /*
        Login auf die Seite, und Navigation zu den DropDown Beispielen
         */
        //------------------------------------------------------------------------------------
        WebElement inputUserName = driver.findElement(By.id("__ac_name"));
        inputUserName.sendKeys("selenium42");

        WebElement inputPassword = driver.findElement(By.id("__ac_password"));
        inputPassword.sendKeys("R5vxI0j60");

        WebElement btnLogin = driver.findElement(By.cssSelector("input.btn"));
        btnLogin.click();

        WebElement btnMenu = driver.findElement(By.id("portaltab-burger-menu"));
        btnMenu.click();

        WebElement linkSideMenuSelenium = driver.findElement(By.linkText("Selenium Testapplikationen"));
        linkSideMenuSelenium.click();

        WebElement linkSeleniumTestApp = driver.findElement(By.linkText("Drag and Drop Beispiel"));
        linkSeleniumTestApp.click();

        // -- Auswahl der DragDrop Elemente
        WebElement drgLogoWhite = driver.findElement(By.id("white-logo"));
        WebElement drgLogoBlue = driver.findElement(By.id("blue-logo"));
        WebElement drgLogoRed = driver.findElement(By.id("red-logo"));
        WebElement drgLogoCS = driver.findElement(By.id("coding-logo"));

        // -- DropBox
        WebElement drpBox = driver.findElement(By.id("droppable"));


        // Act - Die Elemente in die Box ziehen
        //------------------------------------------------------------------------------------
        Actions action = new Actions(driver);
        action.dragAndDrop(drgLogoWhite, drpBox).build().perform();
        action.dragAndDropBy(drgLogoBlue, 180, 10).build().perform();
        action.dragAndDropBy(drgLogoRed, 250, 0).build().perform();

        // -- MouseEvent
        action.clickAndHold(drgLogoCS).perform();
        action.moveByOffset(120, -10).perform();
        action.release(drgLogoCS).perform();

        // Assert - vergleiche das Ergebniss
        //------------------------------------------------------------------------------------
        String erfolgsMeldung = driver.findElement(By.cssSelector("#droppable > p")).getText();
        assertTrue(erfolgsMeldung.contains("coding-logo"));

    }


    @After
    public void tearDown() {
        System.out.println("Driver/Test wurde geschlossen");
        driver.close();
    }

}
