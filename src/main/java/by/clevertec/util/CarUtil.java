package by.clevertec.util;

import by.clevertec.model.Car;
import java.util.Arrays;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CarUtil {

    private final Predicate<Car> isTurkmenistan = car -> car.getColor().equals("White") || car.getColor().equals("Jaguar");
    private final Predicate<Car> isUzbekistan = car -> car.getMass() <= 1500 && (car.getCarMake().equals("BMW") || car.getCarMake().equals("Lexus") || car.getCarMake().equals("Chrysler") || car.getCarMake().equals("Toyota"));
    private final Predicate<Car> isKazakhstan = car -> car.getMass() > 4000 && (car.getColor().equals("Black") || car.getCarMake().equals("GMC") || car.getCarMake().equals("Dodge"));
    private final Predicate<Car> isKyrgyzstan = car -> car.getReleaseYear() <= 1982 || car.getCarModel().equals("Civic") || car.getCarModel().equals("Cherokee");
    private final Predicate<Car> isRussia = car -> !Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) || car.getPrice() > 40000;
    private final Predicate<Car> isMongolia = car -> car.getVin().contains("59");

    public static String getKey(Car car) {
        if (isTurkmenistan.test(car)) {
            return "Turkmenistan";
        } else if (isUzbekistan.test(car)) {
            return "Uzbekistan";
        } else if (isKazakhstan.test(car)) {
            return "Kazakhstan";
        } else if (isKyrgyzstan.test(car)) {
            return "Kyrgyzstan";
        } else if (isRussia.test(car)) {
            return "Russia";
        } else if (isMongolia.test(car)) {
            return "Mongolia";
        } else {
            return "Junkyard";
        }
    }
}
