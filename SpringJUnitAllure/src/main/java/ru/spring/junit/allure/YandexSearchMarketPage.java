package ru.spring.junit.allure;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.tika.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Главная страница яндекса и переход в яндекс маркет
 */
public class YandexSearchMarketPage extends AbstractPage {
    private static Logger log = LoggerFactory.getLogger(YandexSearchMarketPage.class);
    private WebDriverWait wait;

    //Панель с разделами на главной странице Яндекс
    @FindBy(xpath = "//*[contains(@class, 'home-tabs__search')]")
    private List<WebElement> homeTabs;

    public YandexSearchMarketPage(){
        wait = new WebDriverWait(driver, 10);
    }


    /**
     * Идем на главную страницу Яндекс
     * @param address
     */
    @Step
    public void loadUrl(String address) {
        driver.navigate().to("http://yandex.ru");
        try {
            saveCsvAttachment();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Working method loadUrl");
    }


    /**
     * Прикрепляем картинку к шагу loadUrl
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    //Если @Attachment содержит скобки, значит какой то файл цепляем
    @Attachment(value = "Sample image attachment", type = "image/png")
    public byte[] saveCsvAttachment() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("yandexMainPage.png");
        if (resource == null) {
            fail("Couldn't find resource 'yandexMainPage.png'");
        }
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }

    /**
     * Переход в раздел Маркет
     * @return этот объект
     */
    //Если @Attachment не содержит скобок значит, просто вывод полного класса
    @Attachment
    @Step
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
}