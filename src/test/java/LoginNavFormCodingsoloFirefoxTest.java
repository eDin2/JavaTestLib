import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginNavFormCodingsoloFirefoxTest {
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

        WebElement linkSeleniumTestApp = driver.findElement(By.linkText("Selenium Test Form1"));
        linkSeleniumTestApp.click();


        // Act - Durch die Fenster navigieren
        //------------------------------------------------------------------------------------
        // -- Betreff
        driver.findElement(By.id("form-widgets-betreff")).sendKeys("Saban");
        // -- Name
        driver.findElement(By.id("form-widgets-name")).sendKeys("Saulic");

        // -- Kurswahl aus DropDown
        Select selectAuswahl1 = new Select(driver.findElement(By.id("form-widgets-auswahl1")));
        selectAuswahl1.selectByVisibleText("Selenium Automatisierung mit Dieter");

        // -- Auswahl für Firmen
        Select selectAuswahl2 = new Select(driver.findElement(By.id("form-widgets-auswahl2-from")));
        selectAuswahl2.selectByIndex(2);
        selectAuswahl2.selectByIndex(5);
        selectAuswahl2.selectByIndex(8);

        // -- Die Selectierten Firmen übertragen
        driver.findElement(By.name("from2toButton")).click();

        // -- Speichern der Auswahl
        driver.findElement(By.name("form.buttons.speichern")).click();


        // Assert - vergleiche das Ergebniss
        //------------------------------------------------------------------------------------
        String erfolgsMeldung = driver.findElement(By.id("message")).getText();
        assertTrue(erfolgsMeldung.contains("Selenium Automatisierung"));

    }


    @After
    public void tearDown() {
        System.out.println("Driver/Test wurde geschlossen");

        String erstesElement = driver.findElement(By.xpath("//*[@id=\'companies\']/li[1]")).getText();
        System.out.println(erstesElement);

        driver.close();
    }

}
