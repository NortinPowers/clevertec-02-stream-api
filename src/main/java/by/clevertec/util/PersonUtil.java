package by.clevertec.util;

import by.clevertec.model.Person;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonUtil {

    public static boolean getFilterConditionByPersonAge(Person person, int minAge, int maxAge) {
        return person.getDateOfBirth().isAfter(LocalDate.now().minusYears(maxAge))
                && person.getDateOfBirth().isBefore(LocalDate.now().minusYears(minAge));
    }
}
