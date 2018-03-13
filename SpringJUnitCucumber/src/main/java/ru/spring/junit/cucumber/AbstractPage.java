package ru.spring.junit.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


/**
 * Абсрактный класс для расширения страниц. Инициализирует драйвер и элементы через @FindBy
 */
public abstract class AbstractPage {
    protected WebDriver driver;


    /**
     * Дефолтный конструктор. Получаем драйвер для взаимодействия с ним,
     * а так же инициализируем элементы на Page'ах, которые были заданы с помощью аннотаций @FindBy
     */
    public AbstractPage() {
        driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public abstract void loadUrl(String address);
}