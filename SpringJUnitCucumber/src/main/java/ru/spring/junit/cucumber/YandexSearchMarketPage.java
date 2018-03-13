package ru.spring.junit.cucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Главная страница яндекса и переход в яндекс маркет
 */
public class YandexSearchMarketPage extends AbstractPage {
    private static Logger log = LoggerFactory.getLogger(YandexSearchMarketPage.class);
    private WebDriverWait wait;

    @FindBy(xpath = "//*[contains(@class, 'home-tabs__search')]")
    private List<WebElement> homeTabs;

    public YandexSearchMarketPage(){
        wait = new WebDriverWait(driver, 10);
    }

    public void loadUrl(String address) {
        driver.navigate().to("http://yandex.ru");
        log.info("Working method loadUrl");
    }

    /**
     * Перейти в маркет
     * @return этот объект
     */
    //Если @Attachment не содержит скобок значит, просто вывод полного имени файла
    public YandexSearchMarketPage goToMarket(){
        for (WebElement element : homeTabs){
            if (element.getText().equals("Маркет")) {
                element.click();
                log.info("Working method goToMarket");
                break;
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public YandexSearchMarketPage load(String url) {
        driver.get(url);
        return this;
    }

    public YandexSearchMarketPage goByLinkText(String linkText) {
        List<WebElement> items = driver.findElements(By.linkText(linkText));
        for (WebElement item : items)
            try {
                item.click();
            } catch (Exception ex) { }
        return this;
    }

    public YandexSearchMarketPage setPriceById(String id, String price) {
        driver.findElement(By.id(id)).click();
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys(price);
        return this;
    }

    public YandexSearchMarketPage selectCheckById(String id) {
        driver.findElement(By.xpath("//label[@for='" + id + "']")).click();
        if (!driver.findElement(By.id(id)).isSelected())
            driver.findElement(By.id(id)).click();
        return this;
    }

    public YandexSearchMarketPage clickButtonByXpath(String xpath, int wait) {
        driver.findElement(By.xpath(xpath)).click();
        if (wait > 0)
            Tools.sleep(wait);
        return this;
    }

    public YandexSearchMarketPage enterSearchText(String text, String id) {
        driver.findElement(By.id(id)).sendKeys(text);
        return this;
    }

    public YandexSearchMarketPage search(String name, String id, String xpath, int wait) {
        enterSearchText(name, id).
                clickButtonByXpath(xpath, wait);
        return this;
    }
}