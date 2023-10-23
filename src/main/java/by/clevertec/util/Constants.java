package by.clevertec.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final Integer AGE_TEN_YEARS = 10;
    public static final Integer AGE_TWENTY_YEARS = 20;
    public static final Integer AGE_THIRTY_YEARS = 30;
    public static final Integer SUBLIST_SIZE = 7;
    public static final String BUILDING_TYPE_HOSPITAL = "Hospital";
    public static final Double FARE = 7.14;

    @UtilityClass
    public class Country {

        public static final String JAPANESE = "Japanese";
        public static final String HUNGARIAN = "Hungarian";
        public static final String OCEANIA = "Oceania";
    }

    @UtilityClass
    public class Region {

        public static final String INDONESIAN = "Indonesian";
    }

    @UtilityClass
    public class Gender {

        public static final String FEMALE = "Female";
        public static final String MALE = "Male";
    }
}
