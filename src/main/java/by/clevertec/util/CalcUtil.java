package by.clevertec.util;

import static by.clevertec.util.Constants.Faculty.CHEMISTRY;
import static by.clevertec.util.Constants.Faculty.COMPUTER_SCIENCE;
import static by.clevertec.util.Constants.Faculty.MATHEMATICS;
import static by.clevertec.util.Constants.Faculty.PHYSICS;

import by.clevertec.model.Examination;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
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

    public static void selectFacultyWithHightGrade(AtomicReference<Double> physicsAverageMark, AtomicReference<Double> computerScienceSumMark, AtomicReference<Double> mathematicsAverageMark, AtomicReference<Double> chemistryAverageMark, AtomicReference<String> facultyWithHighAverageGradeOnFirstExam) {
        facultyWithHighAverageGradeOnFirstExam.updateAndGet(value -> physicsAverageMark.get() > computerScienceSumMark.get()
                ? PHYSICS
                : computerScienceSumMark.get() > mathematicsAverageMark.get()
                ? COMPUTER_SCIENCE
                : mathematicsAverageMark.get() > chemistryAverageMark.get()
                ? MATHEMATICS
                : CHEMISTRY);
    }

    public static void setFacultyMark(AtomicReference<Double> physicsSumMark, AtomicReference<Integer> physicsCount, AtomicReference<Double> physicsAverageMark, AtomicReference<Double> computerScienceSumMark, AtomicReference<Integer> computerScienceCount, AtomicReference<Double> computerScienceAverageMark, AtomicReference<Double> mathematicsSumMark, AtomicReference<Integer> mathematicsCount, AtomicReference<Double> mathematicsAverageMark, AtomicReference<Double> chemistrySumMark, AtomicReference<Integer> chemistryCount, AtomicReference<Double> chemistryAverageMark, Student student, Optional<Examination> optional) {
        switch (student.getFaculty()) {
            case PHYSICS ->
                    physicsAverageMark.updateAndGet(value -> getAverageFirstExamMark(physicsSumMark, optional, physicsCount));
            case COMPUTER_SCIENCE ->
                    computerScienceAverageMark.updateAndGet(value -> getAverageFirstExamMark(computerScienceSumMark, optional, computerScienceCount));
            case MATHEMATICS ->
                    mathematicsAverageMark.updateAndGet(value -> getAverageFirstExamMark(mathematicsSumMark, optional, mathematicsCount));
            case CHEMISTRY ->
                    chemistryAverageMark.updateAndGet(value -> getAverageFirstExamMark(chemistrySumMark, optional, chemistryCount));
            default ->
                    throw new IllegalStateException("Unexpected value: " + student.getFaculty());
        }
    }
}
