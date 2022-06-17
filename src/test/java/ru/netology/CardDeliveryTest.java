package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import ru.netology.PageObject.PageObject;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    PageObject page = new PageObject();

    @Test
    void shouldGetSuccessMessage() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").val("Калининград");
        $("[data-test-id=\"date\"] .input__control").val(page.setDateNotEarlyThreeDays());
        $("[data-test-id=\"name\"] .input__control").val("Концевич Сергей");
        $("[data-test-id=\"phone\"] .input__control").val("+79245632145");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("button .button__content").click();
        $("[data-test-id=\"notification\"] .notification__content").should(Condition.visible, Duration.ofSeconds(15));
    }
}
