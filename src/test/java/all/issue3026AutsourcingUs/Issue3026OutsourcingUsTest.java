package all.issue3026AutsourcingUs;

import all.TestBase;
import all.issue3025TransferUs.Issue3025TransferUsMainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Issue3026OutsourcingUsTest extends TestBase {

    @Test
    public void test3026OutsourcingUs() throws Exception {
        //arrange
        var page = new Issue3026AutsourcingUsMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.issueOutsourcing.click();
        page.getSummRows();
//        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.insertActionDate();
        page.choiceSst("//div[text()='1234-Минская обл., Несвижский р-н., г. Несвиж, ул. Сосновая, 78']");
        page.textArea.sendKeys("создана автотестом!");
        page.mouseOverToCassette();
        page.addIssue.click();
        page.scribeIssue();
        page.getSummRows();
        page.getWatchIssues();
        page.parseRowsIssue();
        page.parseRowsIssueAttachments();
        //assert
        var expectedResultSign = "Подпись прошла успешно";
        var expectedResult = "Подписано банком";

        Assertions.assertAll(
                ()-> Assertions.assertEquals(expectedResult, page.status.getText(), "Заявка не подписана"), //проверка на соответствие значения string статуса
                ()-> Assertions.assertEquals(expectedResultSign, page.signPopup.getText(), "Заявка не подписана") //проверка соответствия наименования об успешном подписания заявки в попапе
        );

//        page.signPopup.click();
    }
}
