package ru.spring.junit.allure;

import io.qameta.allure.Attachment;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

public class Test {

    //Контекст Spring
    ApplicationContext ctx;
    private static Logger log = LoggerFactory.getLogger(YandexSearchMarketPage.class);

    String URL = "http://yandex.ru";
    AbstractPage abstractPage = null;
    YandexSearchMarketPage yandexSearchPage = null;

    @Before
    public void setUp(){
        log.info("Starting configuration...");
        ctx = new ClassPathXmlApplicationContext("beans.xml");
    }

    @org.junit.Test
    public void test(){
        yandexSearchPage = (YandexSearchMarketPage) ctx.getBean("YandexSearchMarketPage");
        yandexSearchPage.loadUrl(URL);
        yandexSearchPage.goToMarket();
    }

    @After
    public void tearDown(){
        log.info("Close configuration...");
        yandexSearchPage.driver.close();
    }
}
