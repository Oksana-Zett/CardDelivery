package ru.netology.rest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    public void setUp() {
        Configuration.headless = true;
        open("http://localhost:9999");
    }

    @Test
    public void shouldSubmitRequest() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[class='notification__content']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }

    @Test
    public void shouldCityEmpty() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldCitySubjectCenter() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $$("[class=menu-item__control]").contains(text("Москва"));
        $("[data-test-id='city'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[class='notification__content']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }

    @Test
    public void shouldCityNotSubjectCenter() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Химки");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
        $("[data-test-id='city'] input").sendKeys(Keys.CONTROL + "a");
    }

    @Test
    public void shouldDateEarly() {
        String planningDate = generateDate(2);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }

    @Test
    public void shouldNameLatin() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Ivanov Ivan");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldNameNumber() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("123796");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldNameSpecialSymbol() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("!@#%^&*");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldNameEmpty() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldPhoneLetterCyrillic() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("илилоджлиаек");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldPhoneLetterLatin() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("kjklkl");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldPhoneEmpty() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    //
    @Test
    public void shouldPhoneWithoutPlus() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldPhoneWithPlusBetweenNumeral() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("792700+00000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldPhoneWithPlusAfterNumeral() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("79270000000+");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldPhoneSpecialSymbol() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("##&&(!@");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldPhoneOneNumeral() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+9");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldPhoneTwelveNumeral() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+792700000001");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldConsentFlagNotSet() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(byText("Забронировать")).click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}