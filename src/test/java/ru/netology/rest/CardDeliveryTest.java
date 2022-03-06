package ru.netology.rest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;



public class CardDeliveryTest {

    @BeforeEach
    public void setUp (){
        Configuration.headless=true;
    }

    @Test
    public void shouldSubmitRequest() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));

    }

    @Test
    public void shouldCityEmpty() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldCitySubjectCenter() {
        String citySubjectCenter[] = {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар",
                "Симферополь", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита",
                "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань",
                "Белгород", "Брянск", "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курган",
                "Курск", "Липецк", "Магадан", "Мурманск", "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза",
                "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск",
                "Челябинск", "Ярославль", "Москва", "Санкт-Петербург", "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"};
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        for (String item : citySubjectCenter) {
            $("[data-test-id='city'] input").sendKeys(item);
            $$("[class=menu-item__control]").contains(text(item));
            $("[data-test-id='city'] input").sendKeys(Keys.CONTROL + "a");
        }
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
    }

    @Test
    public void shouldCityNotSubjectCenter() {
        String cityNotSubjectCenter[] = {"Люберцы", "Химки", "Париж", "Стокгольм", "Прага", "Осло"};
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        for (String item : cityNotSubjectCenter) {
            $("[data-test-id='city'] input").sendKeys(item);
            //$$("[class=menu-item__control]").contains(text(item));
            $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
            $("[data-test-id='date'] input").sendKeys(str);
            $("[data-test-id='name'] input").sendKeys(Keys.CONTROL + "a");
            $("[data-test-id='name'] input").sendKeys("Иванов Иван");
            $("[data-test-id='phone'] input").sendKeys(Keys.CONTROL + "a");
            $("[data-test-id='phone'] input").sendKeys("+79270000000");
            $(".checkbox__box").click();
            $(byText("Забронировать")).click();
            $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
            $("[data-test-id='city'] input").sendKeys(Keys.CONTROL + "a");
        }
    }

    @Test
    public void shouldDateEarly() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 2); // увеличиваем на 2 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }

    @Test
    public void shouldNameLatin() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Ivanov Ivan");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldNameNumber() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("123796");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldNameSpecialSymbol() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("!@#%^&*");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldNameEmpty() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldPhoneLetterCyrillic() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("илилоджлиаек");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldPhoneLetterLatin() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("kjklkl");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //
    @Test
    public void shouldPhoneEmpty() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    //
    @Test
    public void shouldPhoneWithoutPlus() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("79270000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //
    @Test
    public void shouldPhoneWithPlusBetweenNumeral() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("792700+00000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //
    @Test
    public void shouldPhoneWithPlusAfterNumeral() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("79270000000+");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //
    @Test
    public void shouldPhoneSpecialSymbol() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("##&&(!@");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //
    @Test
    public void shouldPhoneOneNumeral() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+9");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //
    @Test
    public void shouldPhoneTwelveNumeral() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+792700000001");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldConsentFlagNotSet() {
        open("http://localhost:9999");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String str = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(str);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79270000000");
        $(byText("Забронировать")).click();
        $("[class=checkbox__text]").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}