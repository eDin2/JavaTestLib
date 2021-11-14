import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class FalscherLoginCodingsoloFirefoxTest {
    WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("Initalsiere Webdriver");
        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://seleniumkurs.codingsolo.de");
    }


    @Test
    public void testLogin() {
        System.out.println("Test gestartet!");

        // Arrange - zuerst under Eingabefelder befüllen
        //------------------------------------------------------------------------------------
        driver.findElement(By.id("__ac_name")).sendKeys("Falsch");
        driver.findElement(By.id("__ac_password")).sendKeys("Falsch");

        // Act - Login button betätigen
        //------------------------------------------------------------------------------------
        driver.findElement(By.cssSelector("input.btn")).click();

        // Assert - vergleichen das Ergebniss
        //------------------------------------------------------------------------------------
        String fehlerMeldung = driver.findElement(By.cssSelector("div.portalMessage:nth-child(1)")).getText();
        assertTrue(fehlerMeldung.contains("Anmeldung fehlgeschlagen"));
    }


    @After
    public void tearDown() {
        System.out.println("Driver/Test wurde geschlossen");
        driver.close();
    }

}
