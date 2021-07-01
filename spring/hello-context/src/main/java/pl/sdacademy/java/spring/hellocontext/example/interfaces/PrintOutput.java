package pl.sdacademy.java.spring.hellocontext.example.interfaces;

import org.springframework.stereotype.Component;

@Component
public class PrintOutput implements Output{

    @Override
    public boolean supports(Document document) {
        return document.isPrintable();
    }

    @Override
    public void handle(Document document) {
        System.out.println("Print: " + document.getContent());
    }
}
