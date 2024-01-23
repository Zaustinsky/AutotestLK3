package all.issue3025TransferUs;

import all.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Issue3025TransferUsTestIncass extends TestBase {

    @Test
    public void test3025TransferUsIncass() throws Exception { //заявка на инкассацию
        //arrange
        var page = new Issue3025TransferUsMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Инкассация']");
        page.insertFromDate();
        page.choicePointBase("//div[text()='03149 - Точка инкассации клиента Несвиж']");
        page.insertActionDate();
        page.choiceSst("//div[text()='1234-Минская обл., Несвижский р-н., г. Несвиж, ул. Сосновая, 78']");
        page.textArea.sendKeys("создана автотестом!");
        page.mouseOverToCassette();
        page.addIssue.click();
        page.scribeIssue();
        page.getWatchIssues();
        page.parseRowsIssue();
        page.parseRowsIssueAttachments();
        page.getSummRows();
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
