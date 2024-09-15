import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragNDropTest {
    @BeforeAll
    static void setUp(){
        Configuration.baseUrl = ("https://the-internet.herokuapp.com");
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void dragNDropWithMoveByOffsetTest(){
        open("/drag_and_drop");

        $("#column-a header").shouldHave(text("A"));
        $("#column-b header").shouldHave(text("B"));

        WebElement columnA = $("#column-a");
        WebElement columnB = $("#column-b");
        Point columnAPoint = columnA.getLocation();
        Point columnBPoint = columnB.getLocation();
        int x = columnBPoint.getX() - columnAPoint.getX();
        int y = columnBPoint.getY() - columnAPoint.getY();

        actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(x,y).release().perform();

        $("#column-a header").shouldHave(text("B"));
        $("#column-b header").shouldHave(text("A"));
    }

    @Test
    void dragNDropWithSelenideDragNDropTest(){
        open("/drag_and_drop");

        $("#column-a header").shouldHave(text("A"));
        $("#column-b header").shouldHave(text("B"));

        actions().dragAndDrop($("#column-a"),$("#column-b")).perform();

        $("#column-a header").shouldHave(text("B"));
        $("#column-b header").shouldHave(text("A"));
    }
}
