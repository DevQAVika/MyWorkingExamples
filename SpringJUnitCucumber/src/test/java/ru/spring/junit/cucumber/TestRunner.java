package ru.spring.junit.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Runner cucumber test for market.yandex.ru
 *
 * @author Ibraimova Vika
 * @version 0.1 dated Dec 01, 2017
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "ru.spring.junit.cucumber.stepdefinitions"
)
public class TestRunner { }