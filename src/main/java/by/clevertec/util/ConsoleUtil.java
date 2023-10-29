package by.clevertec.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsoleUtil {

    public static void getTaskMark(int taskNumber) {
        System.out.printf("\nSolution of task %d:\n\n", taskNumber);
    }
}
