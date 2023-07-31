package ru.netology.patterns.carddelivery.data;

import com.github.javafaker.Faker;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }
    public static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    public static String generateName() {
        return faker.name().name();
    }

    public static String generatePhoneNumber() {
        return faker.numerify("+7##########");
    }

    public static String generateCity() {
        var cities = new String[]{
                "Москва", "Воронеж", "Белгород", "Липецк", "Санкт-Петербург", "Пенза", "Самара"
        };
        return cities[new Random().nextInt(cities.length)];

    }

}