package pl.sdacademy.java.spring.hellocontext.example.interfaces;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Document doc1 = new Document("Ala ma kota", true);

        DocumentHandler documentHandler = applicationContext.getBean(DocumentHandler.class);
        documentHandler.handleDocument(doc1);

    }
}
