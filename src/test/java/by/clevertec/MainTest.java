package by.clevertec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import by.clevertec.model.Student;
import by.clevertec.util.ConsoleOutputCapturer;
import by.clevertec.util.Util;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class MainTest {

    private ConsoleOutputCapturer outputCapturer;

    @BeforeEach
    public void setup() {
        outputCapturer = new ConsoleOutputCapturer();
        outputCapturer.start();
    }

    @Test
    public void test_task22() {
        Student student1 = new Student(1, "John", 18, "Faculty1", "G-1");
        Student student2 = new Student(11, "Bill", 21, "Faculty1", "G-1");
        List<Student> students = Arrays.asList(student1, student2);

        try (MockedStatic<Util> mockedStatic = Mockito.mockStatic(Util.class)) {
            mockedStatic.when(Util::getStudents).thenReturn(students);

            outputCapturer.start();
            Main.task22();

            String expectedOutput = "\nSolution of task 22:\n\n" +
                    "Faculty: Faculty1, min age of students: 18\n";
            assertEquals(expectedOutput.trim(), outputCapturer.stop());
        }
    }
}
