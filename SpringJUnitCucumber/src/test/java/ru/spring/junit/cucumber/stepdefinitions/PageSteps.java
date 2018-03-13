package ru.spring.junit.cucumber.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.spring.junit.cucumber.DriverManager;
import ru.spring.junit.cucumber.YandexSearchMarketPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Steps for cucumber test for market.yandex.ru
 *
 * @author Ibraimova Vika
 * @version 0.1 dated Dec 01, 2017
 */
public class PageSteps {
    //Контекст Spring
    static ApplicationContext ctx;
    YandexSearchMarketPage yandexSearchMarketPage = null;

    static WebDriver driver;
    static Map<String, String> sets;
    static List<WebElement> list;
    static String notebookName;

    // static initialization block
    static {
        //System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\SourceCode\\Java\\Selenium\\geckodriver.exe");
        driver = DriverManager.getDriver();
        ctx = new ClassPathXmlApplicationContext("beans.xml");
        sets = new HashMap<String, String>();
        sets.put("HP", "glf-7893318-152722");
        sets.put("Lenovo", "glf-7893318-152981");
        sets.put("Apply", "//div[@class='n-filter-panel-aside__apply']//button[.='Применить']");
        sets.put("Name", "//div[@class='n-title__text']");
    }

    @Given("^user opens the site '(.+)'$")
    public void openPage(String url) {
        yandexSearchMarketPage = (YandexSearchMarketPage) ctx.getBean("YandexSearchMarketPage");
        yandexSearchMarketPage.load(url);
    }

    @And("^he goes to '(.+)'$")
    public void goSection(String linkText) {
        yandexSearchMarketPage.goByLinkText(linkText);
    }

    /*@And("^he sets up limit of prices '(.+)'$")
    public void setPriceToById(String price) {
        yandexSearchMarketPage.setPriceById("glf-priceto-var", price);
    }*/

    @And("^he chooses the brand '(.+)'$")
    public void chooseBrand(String brand) {
        yandexSearchMarketPage.selectCheckById(sets.get(brand));
    }

    @And("^he clicks '(.+)'$")
    public void clickButton(String button) {
        yandexSearchMarketPage.clickButtonByXpath(sets.get(button), 10);
    }


    @And("^he saves first items name$")
    public void saveFirstItem() {
        notebookName = list.get(0).getText();
    }

    @And("^he put saved name in the search line$")
    public void putTextInSearch() {
        yandexSearchMarketPage.enterSearchText(notebookName, "header-search");
    }

    @Then("^he takes '(.+)' and compares with saved$")
    public void compareWithSaved(String name) {
        WebElement title = driver.findElement(By.xpath(sets.get(name)));
        Assert.assertEquals(notebookName, title.getText());
        driver.close();
    }

}