package all.issue3026OutsourcingUs;

import all.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Issue3026OutsourcingUsTestLoad extends TestBase { //заявка на загрузку

    @Test
    public void test3026OutsourcingUsLoad() throws Exception {
        //arrange
        var page = new Issue3026OutsourcingUsMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.issueOutsourcing.click();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Загрузка']");
        page.insertActionDate();
        page.choiceSst("//div[text()='1234-Минская обл., Несвижский р-н., г. Несвиж, ул. Сосновая, 78']");
        page.textArea.sendKeys("создана автотестом!");
        page.mouseOverToCassette();
        page.addIssue.click();
        page.scribeIssue();
        page.getWatchIssues();
        page.getSummRows();
        page.parseRowsIssue();
        page.parseRowsIssueAttachments();
        //assert
        var expectedResultSign = "Подпись прошла успешно";
        var expectedResult = "Подписано банком";

        Assertions.assertAll(
                ()-> Assertions.assertEquals(expectedResult, page.status.getText(), "Заявка не подписана"), //проверка на соответствие значения string статуса
                ()-> Assertions.assertEquals(expectedResultSign, page.signPopup.getText(), "Заявка не подписана") //проверка соответствия наименования об успешном подписания заявки в попапе
        );

        page.signPopup.click();
    }
}
