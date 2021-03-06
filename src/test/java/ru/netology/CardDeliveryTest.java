package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldGetSuccessMessage() {
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(4));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"success-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=\"success-notification\"] .notification__content").shouldHave(Condition.text(DataGenerator.generateDate(4)));
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(7));
        $("button .button__content").click();
        $("[data-test-id=\"replan-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(5));
        $("[data-test-id=\"replan-notification\"] .button").click();
        $("[data-test-id=\"success-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=\"success-notification\"] .notification__content").shouldHave(Condition.text(DataGenerator.generateDate(7)));
    }

    @Test
    void shouldValidationMessageWhenCityWithUpperCase() {
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity().toUpperCase());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"success-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationMessageWhenCityWithLowCase() {
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity().toLowerCase());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"success-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationMessageWhenCityIsEmpty() {
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("???????? ?????????????????????? ?????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenDateIsEmpty() {
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"date\"] .input_invalid .input__sub").getText();
        assertEquals("?????????????? ?????????????? ????????", text);
    }

    @Test
    void shouldValidationMessageWhenFioIsEmpty() {
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("???????? ?????????????????????? ?????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenPhoneIsEmpty() {
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("???????? ?????????????????????? ?????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenCheckboxIsDisabled() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("button .button__content").click();
        String text = $("[data-test-id=\"agreement\"].input_invalid .checkbox__text").getText();
        assertEquals("?? ???????????????????? ?? ?????????????????? ?????????????????? ?? ?????????????????????????? ???????? ???????????????????????? ????????????", text);
    }

    @Test
    void shouldValidationMessageWhenCityWithLatin() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Moscow");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("???????????????? ?? ?????????????????? ?????????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenCityWithNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity() + 3);
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("???????????????? ?? ?????????????????? ?????????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenCityWithSpaceInTheMiddleOfTheWord() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("?????????? ????????????");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("???????????????? ?? ?????????????????? ?????????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenCityWithSymbol() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity() + "!");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("???????????????? ?? ?????????????????? ?????????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenCityFromAnotherCountry() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("??????????????");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("???????????????? ?? ?????????????????? ?????????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenTwoCity() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity() + " " + DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"city\"].input_invalid .input__sub").getText();
        assertEquals("???????????????? ?? ?????????????????? ?????????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenDateNotEarlyTwoDays() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(2));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"date\"] .input_invalid .input__sub").getText();
        assertEquals("?????????? ???? ?????????????????? ???????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenCurrentDate() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(0));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"date\"] .input_invalid .input__sub").getText();
        assertEquals("?????????? ???? ?????????????????? ???????? ????????????????????", text);
    }

    @Test
    void shouldValidationMessageWhenDateForOneMonth() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(31));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"success-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationMessageWhenDateForOneYear() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(365));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"success-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationMessageWhenFioWithNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName() + 1);
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("?????? ?? ?????????????? ???????????????? ??????????????. ?????????????????? ???????????? ?????????????? ??????????, ?????????????? ?? ????????????.", text);
    }

    @Test
    void shouldValidationMessageWhenFioWithSymbols() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName() + "!");
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("?????? ?? ?????????????? ???????????????? ??????????????. ?????????????????? ???????????? ?????????????? ??????????, ?????????????? ?? ????????????.", text);
    }

    @Disabled
    @Test
    void shouldValidationMessageWhenFioContains??() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val("????????????????-???????????? ????????????!");
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("?????? ?? ?????????????? ???????????????? ??????????????. ?????????????????? ???????????? ?????????????? ??????????, ?????????????? ?? ????????????.", text);
    }

    @Test
    void shouldValidationMessageWhenFioWithLatin() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val("Contevici Sergiu");
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"name\"].input_invalid .input__sub").getText();
        assertEquals("?????? ?? ?????????????? ???????????????? ??????????????. ?????????????????? ???????????? ?????????????? ??????????, ?????????????? ?? ????????????.", text);
    }

    @Disabled
    @DisplayName("?????? ?????????? ???????????????? ????????????????, ???????????? ????????????????????????, ???????????????????? ?????????????????????????????? ????????")
    @Test
    void shouldGetSuccessMessageWhenPhoneWithoutPlus() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val("73927274644");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("button .button__content").click();
        $("[data-test-id=\"success-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=\"success-notification\"] .notification__content").shouldHave(Condition.text(DataGenerator.generateDate(4)));
    }

    @Disabled
    @DisplayName("Bug")
    @Test
    void shouldValidationMessageWhenPhoneLessThan11() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber()).sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("?????????????? ???????????? ??????????????. ???????????? ???????? 11 ????????, ????????????????, +79012345678.", text);
    }

    @Disabled
    @DisplayName("?????? ?????????? ???????????????? ????????????????, ???????????? ????????????????????????, ???????????????????? ?????????????????????????????? ????????")
    @Test
    void shouldValidationMessageWhenPhoneMoreThan11() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val(DataGenerator.generatePhoneNumber() + 2);
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("?????????????? ???????????? ??????????????. ???????????? ???????? 11 ????????, ????????????????, +79012345678.", text);
    }

    @Test
    void shouldGetSuccessMessageWhenPhoneWithAnotherSymbol() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val(DataGenerator.generateFullName());
        $("[data-test-id=\"phone\"] .input__control").val("*79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"success-notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(5));
    }

    @Disabled
    @DisplayName("???? ????????????????????, ?????? ?????? ?????????????????????? ?????????????????? ?? ????????????")
    @Test
    void shouldValidationMessageWhenPlusInTheEnd() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val(DataGenerator.randomCity());
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] .input__control").val(DataGenerator.generateDate(3));
        $("[data-test-id=\"name\"] .input__control").val("????????????????-???????????? ????????????");
        $("[data-test-id=\"phone\"] .input__control").val("79245632145+");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        String text = $("[data-test-id=\"phone\"].input_invalid .input__sub").getText();
        assertEquals("?????????????? ???????????? ??????????????. ???????????? ???????? 11 ????????, ????????????????, +79012345678.", text);
    }
}
