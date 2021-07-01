package pl.sdacademy.java.spring.hellocontext.example.interfaces;

public interface Output {

    boolean supports(Document document);
    void handle(Document document);
}
