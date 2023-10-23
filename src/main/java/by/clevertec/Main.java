package by.clevertec;

import static by.clevertec.util.CalcUtil.getAverageFirstExamMark;
import static by.clevertec.util.CalcUtil.getFilterConditionForRangeOfAgesOfPerson;
import static by.clevertec.util.ConsoleUtil.getTaskMark;
import static by.clevertec.util.Constants.AGE_TEN_YEARS;
import static by.clevertec.util.Constants.AGE_THIRTY_YEARS;
import static by.clevertec.util.Constants.AGE_TWENTY_YEARS;
import static by.clevertec.util.Constants.BUILDING_TYPE_HOSPITAL;
import static by.clevertec.util.Constants.Country.HUNGARIAN;
import static by.clevertec.util.Constants.Country.JAPANESE;
import static by.clevertec.util.Constants.Country.OCEANIA;
import static by.clevertec.util.Constants.FARE;
import static by.clevertec.util.Constants.Faculty.CHEMISTRY;
import static by.clevertec.util.Constants.Faculty.COMPUTER_SCIENCE;
import static by.clevertec.util.Constants.Faculty.MATHEMATICS;
import static by.clevertec.util.Constants.Faculty.PHYSICS;
import static by.clevertec.util.Constants.Gender.FEMALE;
import static by.clevertec.util.Constants.Gender.MALE;
import static by.clevertec.util.Constants.Region.INDONESIAN;
import static by.clevertec.util.Constants.SUBLIST_SIZE;
import static by.clevertec.util.Constants.VaseMaterial.ALUMINUM;
import static by.clevertec.util.Constants.VaseMaterial.GLASS;
import static by.clevertec.util.Constants.VaseMaterial.STEEL;
import static java.lang.Math.min;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static final List<Animal> ANIMALS;
    private static final List<Student> STUDENTS;
    private static final List<Examination> EXAMINATIONS;
    private static final DecimalFormat FORMAT;

    static {
        ANIMALS = Util.getAnimals();
        STUDENTS = Util.getStudents();
        EXAMINATIONS = Util.getExaminations();
        FORMAT = new DecimalFormat("#.##");
    }

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
//        task14();
//        task15();
//        task16();
//        task17();
//        task18();
//        task19();
//        task20();
        task21();
        task22();
    }

    public static void task1() {
        getTaskMark(1);
        List<Animal> list = ANIMALS.stream()
                .filter(animal -> animal.getAge() >= AGE_TEN_YEARS && animal.getAge() <= AGE_TWENTY_YEARS)
                .sorted(Comparator.comparing(Animal::getAge))
                .toList();
        System.out.println("Animals that were distributed to the third zoo:");
        IntStream.range(0, (list.size() + 6) / SUBLIST_SIZE)
                .mapToObj(i -> list.subList(i * SUBLIST_SIZE, min(list.size(), (i + 1) * SUBLIST_SIZE)))
                .toList()
                .get(2)
                .forEach(System.out::println);
    }

    public static void task2() {
        getTaskMark(2);
        ANIMALS.stream()
                .filter(animal -> animal.getOrigin().equals(JAPANESE))
                .peek(animal -> animal.setBreed(animal.getBreed().toUpperCase()))
                .filter(animal -> animal.getGender().equals(FEMALE))
                .forEach(animal -> System.out.println(animal.getBreed()));
    }

    public static void task3() {
        getTaskMark(3);
        ANIMALS.stream()
                .filter(animal -> animal.getAge() > AGE_THIRTY_YEARS)
                .map(Animal::getOrigin)
                .filter(country -> country.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    public static void task4() {
        getTaskMark(4);
        System.out.println("The number of all female animals from the list is " + ANIMALS.stream()
                .filter(animal -> animal.getGender().equals(FEMALE))
                .count());
    }

    public static void task5() {
        getTaskMark(5);
        ANIMALS.stream()
                .filter(animal -> animal.getAge() >= AGE_TWENTY_YEARS && animal.getAge() <= AGE_THIRTY_YEARS)
                .filter(animal -> animal.getOrigin().equals(HUNGARIAN))
                .findAny()
                .ifPresentOrElse(any -> System.out.println("The assumption about the presence of Hungarian animals in the list is correct"),
                        () -> System.out.println("There are no Hungarian animals in the list"));
    }

    public static void task6() {
        getTaskMark(6);
        ANIMALS.stream()
                .filter(animal -> !animal.getGender().equals(FEMALE) && !animal.getGender().equals(MALE))
                .findAny()
                .ifPresentOrElse(any -> System.out.println("The assumption that not all animals have one of the two genders is incorrect"),
                        () -> System.out.println("Animals are divided into only two typical genders"));

    }

    public static void task7() {
        getTaskMark(7);
        System.out.println(ANIMALS.stream()
                .anyMatch(animal -> animal.getOrigin().equals(OCEANIA))
                ? "There are animals from Oceania in the list"
                : "There are no animals from Oceania in the list");
    }

    public static void task8() {
        getTaskMark(8);
        ANIMALS.stream()
                .sorted(Comparator.comparing(Animal::getBreed))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge))
                .ifPresent(animal -> System.out.printf("The age of the oldest animal is %s years old\n", animal.getAge()));
    }

    public static void task9() {
        getTaskMark(9);
        ANIMALS.stream()
                .map(animal -> animal.getBreed().toCharArray())
                .mapToInt(array -> array.length)
                .min()
                .ifPresent(length -> System.out.println("The length of the shortest array is " + length));
    }

    public static void task10() {
        getTaskMark(10);
        System.out.println("The total age of all animals is " + ANIMALS.stream()
                .mapToInt(Animal::getAge)
                .sum());
    }

    public static void task11() {
        getTaskMark(11);
        ANIMALS.stream()
                .filter(animal -> animal.getOrigin().equals(INDONESIAN))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresentOrElse(age -> System.out.println("The average age of the animals in Indonesia is " + age),
                        () -> System.out.println("There are no animals from Indonesia in the list"));
    }

    public static void task12() {
        getTaskMark(12);
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> person.getGender().equals(MALE))
                .filter(person -> getFilterConditionForRangeOfAgesOfPerson(person, 18, 27))
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(person -> System.out.println("Ready to serve: " + person));
    }

    public static void task13() {
        getTaskMark(13);
        List<House> houses = Util.getHouses();
        Stream.concat(
                        houses.stream()
                                .filter(house -> house.getBuildingType().equals(BUILDING_TYPE_HOSPITAL))
                                .flatMap(house -> house.getPersonList().stream()),
                        Stream.concat(
                                houses.stream()
                                        .filter(house -> !house.getBuildingType().equals(BUILDING_TYPE_HOSPITAL))
                                        .flatMap(house -> house.getPersonList().stream())
                                        .filter(person -> !getFilterConditionForRangeOfAgesOfPerson(person, 18, 58)),
                                houses.stream()
                                        .filter(house -> !house.getBuildingType().equals(BUILDING_TYPE_HOSPITAL))
                                        .flatMap(house -> house.getPersonList().stream())
                        )
                )
                .distinct()
                .limit(500)
                .forEach(System.out::println);
    }

    public static void task14() {
        getTaskMark(14);
        List<Car> cars = Util.getCars();
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
        cars.stream()
                .collect(Collectors.groupingBy(car -> {
                    if (car.getColor().equals("White") || car.getColor().equals("Jaguar")) {
                        return "Turkmenistan";
                    } else if (car.getMass() <= 1500 && (car.getCarMake().equals("BMW") || car.getCarMake().equals("Lexus") || car.getCarMake().equals("Chrysler") || car.getCarMake().equals("Toyota"))) {
                        return "Uzbekistan";
                    } else if (car.getMass() > 4000 && (car.getColor().equals("Black") || car.getCarMake().equals("GMC") || car.getCarMake().equals("Dodge"))) {
                        return "Kazakhstan";
                    } else if (car.getReleaseYear() <= 1982 || car.getCarModel().equals("Civic") || car.getCarModel().equals("Cherokee")) {
                        return "Kyrgyzstan";
                    } else if (!Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) || car.getPrice() > 40000) {
                        return "Russia";
                    } else if (car.getVin().contains("59")) {
                        return "Mongolia";
                    } else {
                        return "Junkyard";
                    }
                })).values().stream()
                .limit(6)
                .forEach(list -> {
                    System.out.println("Fare: " + FORMAT.format(FARE * list.stream()
                            .mapToLong(Car::getMass)
                            .sum()));
                    totalPrice.updateAndGet(value -> value + list.stream()
                            .mapToDouble(Car::getPrice)
                            .sum() - FARE * list.stream()
                            .mapToDouble(Car::getMass)
                            .sum());
                });
        System.out.println("Total revenue: " + FORMAT.format(totalPrice.get()));
    }

    public static void task15() {
        getTaskMark(15);
        List<Flower> flowers = Util.getFlowers();
        int year = 5;
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
        flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(flower -> Pattern.matches("^[C-S].*", flower.getCommonName()))
                .filter(Flower::isShadePreferred)
                .filter(flower -> flower.getFlowerVaseMaterial().contains(GLASS) || flower.getFlowerVaseMaterial().contains(ALUMINUM) || flower.getFlowerVaseMaterial().contains(STEEL))
                .toList()
                .forEach(flower -> {
                    double oneFlowerPrice = flower.getPrice() + (flower.getWaterConsumptionPerDay() * 1.39 * year);
                    System.out.println("The cost of servicing one " + flower.getCommonName() + " for " + year + " years is " + FORMAT.format(oneFlowerPrice) + "$");
                    totalPrice.updateAndGet(value -> value + oneFlowerPrice);
                });
        System.out.println("\nThe cost of servicing of the flower garden for " + year + " years is " + FORMAT.format(totalPrice.get()) + "$");
    }

    public static void task16() {
        STUDENTS.stream()
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(student -> System.out.println(student.getSurname() + ", age:" + student.getAge()));
    }

    public static void task17() {
        STUDENTS.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        STUDENTS.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingInt(Student::getAge)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println("Faculty: " + entry.getKey() + ", average age of students: " + FORMAT.format(entry.getValue())));
    }

    public static void task19() {
        String group = "P-1";
        int examNumber = 4;
        System.out.println("List of students from group " + group + " who passed exam #" + examNumber);
        EXAMINATIONS.stream()
                .filter(examination -> examination.getExam3() > examNumber)
                .map(Examination::getStudentId)
                .forEach(id -> {
                    Optional<Student> studentOptional = STUDENTS.stream()
                            .filter(student -> id == student.getId())
                            .filter(student -> student.getGroup().equals(group))
                            .findFirst();
                    studentOptional.ifPresent(System.out::println);
                });
    }

    public static void task20() {
        AtomicReference<Double> physicsSumMark = new AtomicReference<>((double) 0);
        AtomicReference<Integer> physicsCount = new AtomicReference<>(0);
        AtomicReference<Double> physicsAverageMark = new AtomicReference<>((double) 0);
        AtomicReference<Double> computerScienceSumMark = new AtomicReference<>((double) 0);
        AtomicReference<Integer> computerScienceCount = new AtomicReference<>(0);
        AtomicReference<Double> computerScienceAverageMark = new AtomicReference<>((double) 0);
        AtomicReference<Double> mathematicsSumMark = new AtomicReference<>((double) 0);
        AtomicReference<Integer> mathematicsCount = new AtomicReference<>(0);
        AtomicReference<Double> mathematicsAverageMark = new AtomicReference<>((double) 0);
        AtomicReference<Double> chemistrySumMark = new AtomicReference<>((double) 0);
        AtomicReference<Integer> chemistryCount = new AtomicReference<>(0);
        AtomicReference<Double> chemistryAverageMark = new AtomicReference<>((double) 0);
        AtomicReference<String> facultyWithHighAverageGradeOnFirstExam = new AtomicReference<>();
        STUDENTS.stream()
                .collect(Collectors.groupingBy(Student::getFaculty))
                .forEach((key, value1) -> value1
                        .forEach(student ->
                                EXAMINATIONS.stream()
                                        .filter(examination -> examination.getStudentId() == student.getId())
                                        .findAny()
                                        .ifPresent(mark -> {
                                            Optional<Examination> optional = EXAMINATIONS.stream()
                                                    .filter(examination -> examination.getStudentId() == student.getId())
                                                    .findFirst();
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
                                            facultyWithHighAverageGradeOnFirstExam.updateAndGet(value -> physicsAverageMark.get() > computerScienceSumMark.get()
                                                    ? PHYSICS
                                                    : computerScienceSumMark.get() > mathematicsAverageMark.get()
                                                    ? COMPUTER_SCIENCE
                                                    : mathematicsAverageMark.get() > chemistryAverageMark.get()
                                                    ? MATHEMATICS
                                                    : CHEMISTRY);
                                        })));
        System.out.println("The faculty with a high level of academic performance in the first exam is " + facultyWithHighAverageGradeOnFirstExam);
    }

    public static void task21() {
        STUDENTS.stream()
                .collect(Collectors.groupingBy(Student::getGroup))
                .forEach((key, value) -> System.out.println("Group: " + key + ", number of students: " + value.size()));
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }
}
