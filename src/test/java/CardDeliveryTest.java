import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSendForm(){
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE).setValue(generateDate(3));
        $("[data-test-id='name'] input").setValue("Салтыков-Щедрин Михаил");
        $("[data-test-id='phone'] input").setValue("+79600000000");
        $("[data-test-id='agreement']").click();
        $(byClassName ("button")).click();
        $(withText("Успешно!")).should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification']").should(Condition.text("Встреча успешно забронирована на " + generateDate(3))).should(Condition.visible);
    }
}
