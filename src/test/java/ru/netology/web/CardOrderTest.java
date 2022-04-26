package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {
    LocalDate date = LocalDate.now();
    LocalDate meetingDate = date.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    void shouldOrderTheCard() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] input").setValue("Иркутск");
        $("[data-test-id=\"date\"] input").doubleClick().sendKeys(formatter.format(meetingDate));
        $("[data-test-id=\"name\"] input").setValue("Антонов Денис");
        $("[data-test-id=\"phone\"] input").setValue("+79995771202");
        $("[data-test-id=\"agreement\"]").click();
        $("[class=\"button__text\"]").click();
        $("[data-test-id=\"notification\"]").shouldHave(text("Встреча успешно забронирована на " + formatter.format(meetingDate)), Duration.ofSeconds(15));
    }
}
