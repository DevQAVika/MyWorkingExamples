package ru.spring.junit.allure;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


/**
 * LoggerManager для получения WebDriver.
 * Используется при создании страниц. Вызов getDriver() происходит в AbstractPage.
 */
public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager(){}

    /**
     * @return возвращает WebDriver
     */
    @Step
    public static WebDriver getDriver() {
        if (instance == null) {
            instance = new DriverManager();
            //VRAGA!!!
            System.setProperty("webdriver.gecko.driver", "D:\\SourceCode\\Java\\Selenium\\geckodriver.exe");
            instance.driver = new FirefoxDriver();

            //System.setProperty("webdriver.chrome.driver","./src/test/drivers/chrome/chromedriver.exe");
            //WebDriver driver = new ChromeDriver();

            instance.driver = DriverManager.getDriver();
            instance.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            instance.driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
            instance.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        }
        return instance.driver;
    }
}