import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryApplicationTest {
    public String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    String name = DataGenerator.generateName();
    String phone = DataGenerator.faker.phoneNumber().phoneNumber();

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @Test

    public void validValueTest() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").doubleClick().sendKeys(generateDate(4, "d.MM.yyyy"));
        $("[data-test-id='name'] input").setValue(name);
        $("[data-test-id='phone'] input").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__title").shouldHave(text("Успешно"), Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на  " + generateDate(4, "dd.MM.yyyy")))
                .shouldBe(visible);
        $("[data-test-id='date'] input").doubleClick().sendKeys(generateDate(5, "dd.MM.yyyy"));
        $(".button").click();
        $("[data-test-id='replan-notification'] .notification__title")
                .shouldHave(text("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] button").click();
        $("[data-test-id=success-notification] .notification__title")
                .shouldBe(text("Успешно!"), Duration.ofSeconds(40));
        $(".notification__content")
                .shouldBe(text("Встреча успешно запланирована на  " + generateDate(5, "dd.MM.yyyy")))
                .shouldBe(visible);


    }


}


