package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DebitCardDeliveryTest {

    @BeforeEach
    void prepareForTest() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldAppointMeetingDatePositive() {
        $("input[placeholder='Город']").val("Москва");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        //$("input[placeholder='Дата встречи']").clear();
        //$("input[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        //$("input[placeholder='Дата встречи']").val("22.02.2023").pressEnter();
        //$x("[@class = 'input__control'][contains(text(), 'Город')]").val("Москва");
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[class='notification__title']").shouldBe(visible, Duration.ofSeconds(12));
        $("[data-test-id=notification] .notification__content").should(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    void shouldNotAppointMeetingWithIncorrectDate() {
        $("input[placeholder='Город']").val("Москва");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=date] .input__sub").should(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldNotAppointMeetingWithoutDate() {
        $("input[placeholder='Город']").val("Москва");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val();
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=date] .input__sub").should(exactText("Неверно введена дата"));
    }

    @Test
    void shouldNotAppointMeetingDateWithoutCity() {
        $("input[placeholder='Город']").val("");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=city] .input__sub").should(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotAppointMeetingDateWithAbsentCity() {
        $("input[placeholder='Город']").val("Одинцово");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=city] .input__sub").should(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNotAppointMeetingDateWithIncorrectCity() {
        $("input[placeholder='Город']").val("Vjcrdf");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=city] .input__sub").should(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNotAppointMeetingDateWithoutName() {
        $("input[placeholder='Город']").val("Москва");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=name] .input__sub").should(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotAppointMeetingDateWithNameInEnglish() {
        $("input[placeholder='Город']").val("Москва");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("Anastassiya Misyurova");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=name] .input__sub").should(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotAppointMeetingDateWithoutPhone() {
        $("input[placeholder='Город']").val("Москва");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=phone] .input__sub").should(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotAppointMeetingDateWithIncorrectPhone() {
        $("input[placeholder='Город']").val("Москва");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+791656655666");
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=phone] .input__sub").should(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldAppointMeetingDateWithoutAgreement() {
        $("input[placeholder='Город']").val("Москва");
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys("DELETE");
        String date = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder='Дата встречи']").val(date);
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79165665566");
        $("[class=\"button__content\"]").click();
        $("[data-test-id=agreement] .checkbox__text").should(exactText("Я соглашаюсь с условиями обработки и использования  моих персональных данных"));
    }
}

