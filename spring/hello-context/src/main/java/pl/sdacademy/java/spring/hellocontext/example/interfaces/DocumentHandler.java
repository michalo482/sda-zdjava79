package pl.sdacademy.java.spring.hellocontext.example.interfaces;

import org.springframework.stereotype.Component;

@Component
public class DocumentHandler {

    private final Output output;

    public DocumentHandler(Output output) {
        this.output = output;
    }

    public void handleDocument(Document document) {
        if (output.supports(document)) {
            output.handle(document);
        }
    }
}
