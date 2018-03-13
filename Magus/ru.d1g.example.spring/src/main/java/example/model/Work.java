package main.java.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Work {

    @Autowired
    Person person;

    public Person getPerson() {
        return person;
    }

}
