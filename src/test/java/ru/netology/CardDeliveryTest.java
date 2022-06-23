package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import org.openqa.selenium.Keys;
import ru.netology.dateConfig.GenerateDate;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {

    GenerateDate date = new GenerateDate();

    @Test
    void shouldGetSuccessMessage() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=\"notification\"] .notification__content").shouldHave(Condition.text(date.setDateNotEarlyThreeDays(3)));
    }

    @Test
    void shouldValidationMessageWhenCityWithUpperCase() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("КАЛИНИНГРАД");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationMessageWhenCityWithLowCase() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationMessageWhenCityIsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text);
    }

    @Test
    void shouldValidationMessageWhenDateIsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"date\"] .input_invalid .input__sub").getText();
        assertEquals("Неверно введена дата", text);
    }

    @Test
    void shouldValidationMessageWhenFioIsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text);
    }

    @Test
    void shouldValidationMessageWhenPhoneIsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text);
    }

    @Test
    void shouldValidationMessageWhenCheckboxIsDisabled() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("button .button__content").click();
        String text = $("[data-test-id=\"agreement\"].input_invalid .checkbox__text").getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных", text);
    }

    @Test
    void shouldValidationMessageWhenCityWithLatin() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Moscow");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text);
    }

    @Test
    void shouldValidationMessageWhenCityWithNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград1");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text);
    }

    @Test
    void shouldValidationMessageWhenCityWithSpaceInTheMiddleOfTheWord() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калин инград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text);
    }

    @Test
    void shouldValidationMessageWhenCityWithSymbol() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград!");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text);
    }

    @Test
    void shouldValidationMessageWhenCityFromAnotherCountry() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Кишинев");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text);
    }

    @Test
    void shouldValidationMessageWhenTwoCity() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград Москва");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text);
    }

    @Test
    void shouldValidationMessageWhenDateNotEarlyTwoDays() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(2));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"date\"] .input_invalid .input__sub").getText();
        assertEquals("Заказ на выбранную дату невозможен", text);
    }

    @Test
    void shouldValidationMessageWhenCurrentDate() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(0));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"date\"] .input_invalid .input__sub").getText();
        assertEquals("Заказ на выбранную дату невозможен", text);
    }

    @Test
    void shouldValidationMessageWhenDateForOneMonth() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(31));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationMessageWhenDateForOneYear() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(365));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationMessageWhenFioWithNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей1");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text);
    }

    @Test
    void shouldValidationMessageWhenFioWithSymbols() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей!");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text);
    }

    @Test
    void shouldValidationMessageWhenFioWithLatin() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Contevici Sergiu");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text);
    }

    @Test
    void shouldValidationMessageWhenPhoneWithoutPlus() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
    }

    @Test
    void shouldValidationMessageWhenPhoneLessThan11() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+7924563214");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
    }

    @Test
    void shouldValidationMessageWhenPhoneMoreThan11() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+792456321451");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
    }

    @Test
    void shouldValidationMessageWhenPhoneWithAnotherSymbol() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("*79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
    }

    @Test
    void shouldValidationMessageWhenPlusInTheEnd() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(date.setDateNotEarlyThreeDays(3));
        $("[data-test-id=\"name\"] .input__control").val("Концевич-Иванов Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("79245632145+");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
    }
}
