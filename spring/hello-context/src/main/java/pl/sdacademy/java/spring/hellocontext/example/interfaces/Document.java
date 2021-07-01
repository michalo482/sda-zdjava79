package pl.sdacademy.java.spring.hellocontext.example.interfaces;

public class Document {
    private final String content;
    private final boolean printable;

    public Document(String content, boolean printable) {
        this.content = content;
        this.printable = printable;
    }

    public String getContent() {
        return content;
    }

    public boolean isPrintable() {
        return printable;
    }
}
