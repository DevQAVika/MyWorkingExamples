package main.java.example;

import main.java.example.model.Work;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Программно берем контекст генеренный из XML файла
        ApplicationContext context = new ClassPathXmlApplicationContext("main/resources/spring.xml");

        // Берем из контекста бин класса Work
        Work work = context.getBean(Work.class);
        System.out.println(">>>>>>>>>>> OUTPUT: "+work.getPerson().getName());
    }
}
