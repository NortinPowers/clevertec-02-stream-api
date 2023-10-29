package by.clevertec.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputCapturer {
    private ByteArrayOutputStream outputStream;
    private PrintStream originalPrintStream;

    public void start() {
        originalPrintStream = System.out;
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
    }

    public String stop() {
        System.setOut(originalPrintStream);
        return outputStream.toString().trim();
    }
}
