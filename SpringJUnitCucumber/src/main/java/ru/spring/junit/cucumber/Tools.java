package ru.spring.junit.cucumber;

/**
 * Tools class for testing
 *
 * @author Ibraimova Vika
 * @version dated Mar 01, 2018
 */
public class Tools {

    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}