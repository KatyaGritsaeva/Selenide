package ru.netology.selenide;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryFirst {

    private String generateDate(int addDays, String pattern){
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        //SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue("Уфа");
        String currentDate = generateDate (1, "dd.MM.yyyy");
       // $("[data-test-id=date] input").setValue("07.06.2023");
        $("[data-test-id=name] input").setValue("Тестов Тест");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));
    }
}
