import com.github.javafaker.Faker;


import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    static Faker faker = new Faker(new Locale("ru"));

    public static String generateName() {
        return faker.name().fullName();
    }

}