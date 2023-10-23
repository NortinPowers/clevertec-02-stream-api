package by.clevertec.util;

import by.clevertec.model.Examination;
import by.clevertec.model.Person;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CalcUtil {

    public static boolean getFilterConditionForRangeOfAgesOfPerson(Person person, int minAge, int maxAge) {
        return person.getDateOfBirth().isAfter(LocalDate.now().minusYears(maxAge))
                && person.getDateOfBirth().isBefore(LocalDate.now().minusYears(minAge));
    }

    public static Double getAverageFirstExamMark(AtomicReference<Double> sumMark, Optional<Examination> optional, AtomicReference<Integer> count) {
        sumMark.updateAndGet(value -> value + optional.get().getExam1());
        count.getAndAccumulate(1, Integer::sum);
        return sumMark.get() / count.get();
    }
}
