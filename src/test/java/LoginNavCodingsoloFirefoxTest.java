import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginNavCodingsoloFirefoxTest {
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
        driver.findElement(By.id("__ac_name")).sendKeys("selenium42");
        driver.findElement(By.id("__ac_password")).sendKeys("R5vxI0j60");
        driver.findElement(By.cssSelector("input.btn")).click();


        // Act - Durch die Fenster navigieren
        //------------------------------------------------------------------------------------
        driver.findElement(By.id("portaltab-burger-menu")).click();
        driver.findElement(By.linkText("Selenium Testapplikationen")).click();
        driver.findElement(By.linkText("Selenium Test Form1")).click();


        // Assert - vergleichen das Ergebniss
        //------------------------------------------------------------------------------------
        String actual = driver.findElement(By.tagName("h1")).getText();
        String exctends = "Selenium Test Form1";
        assertEquals(exctends, actual);
    }


    @After
    public void tearDown() {
        System.out.println("Driver/Test wurde geschlossen");
        driver.close();
    }

}
