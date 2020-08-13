package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;

import org.openqa.selenium.Keys;

public class DeliveryCardTest {
        final DataGenerator generator = new DataGenerator();

    @BeforeEach
    void setUp() {
        open("http://localhost:7777");
    }

    @Test
    void shouldSubmitRequest(){
        String date = generator.generateDate();
        $("[data-test-id='city'] input").setValue(generator.generateCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id='name'] input").setValue(generator.generateName());
        $("[data-test-id='phone'] input").setValue(generator.generatePhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='success-notification']").shouldHave(exactText( "Успешно! Встреча успешно запланирована на "
                + date));
    }

    @Test
    void shouldChangedBookingDate(){
        String date = generator.generateDate();
        $("[data-test-id='city'] input").setValue(generator.generateCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generator.generateDate());
        $("[data-test-id='name'] input").setValue(generator.generateName());
        $("[data-test-id='phone'] input").setValue(generator.generatePhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $(withText("Успешно")).shouldBe(visible);
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(date);
        $(".button").click();
        $("[data-test-id='replan-notification']").shouldHave(text("У вас уже запланирована " +
                "встреча на другую дату. Перепланировать?"));
        $(".notification_visible .button").click();
        $("[data-test-id='success-notification']").shouldHave(exactText( "Успешно! Встреча успешно запланирована на "
                + date));
    }

    @Test
    void shouldNotSubmitRequestIfWrongCity(){
        $("[data-test-id='city'] input").setValue(generator.generateWrongCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generator.generateDate());
        $("[data-test-id='name'] input").setValue(generator.generateName());
        $("[data-test-id='phone'] input").setValue(generator.generatePhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNotSubmitIfBookingIsBeforeThreeDays(){
        $("[data-test-id='city'] input").setValue(generator.generateCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generator.todayDate());
        $("[data-test-id='name'] input").setValue(generator.generateName());
        $("[data-test-id='phone'] input").setValue(generator.generatePhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $(" [data-test-id='date'] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldNotSubmitIfWrongName(){
        $("[data-test-id='city'] input").setValue(generator.generateCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generator.generateDate());
        $("[data-test-id='name'] input").setValue(generator.generateWrongName());
        $("[data-test-id='phone'] input").setValue(generator.generatePhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $(" [data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия " +
                "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

   @Test
    void shouldNotSubmitIfPhoneIsWrong(){
       $("[data-test-id='city'] input").setValue(generator.generateCity());
       $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
       $("[data-test-id='date'] input").sendKeys(generator.generateDate());
       $("[data-test-id='name'] input").setValue(generator.generateName());
       $("[data-test-id='phone'] input").setValue(generator.generateWrongPhone());
       $("[data-test-id='agreement'] .checkbox__box").click();
       $(".button").click();
       $(" [data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. " +
             "Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitIfCheckboxUnchecked(){
        $("[data-test-id='city'] input").setValue(generator.generateCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generator.generateDate());
        $("[data-test-id='name'] input").setValue(generator.generateName());
        $("[data-test-id='phone'] input").setValue(generator.generatePhone());
        $(".button").click();
        $(".input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки " +
                "и использования моих персональных данных"));
    }
}
