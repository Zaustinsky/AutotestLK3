package all.issue3029Keeping;

import all.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Issue3029KeepingTestInStorage extends TestBase {

    @Test
    public void test3029KeepingTestInStorage() throws Exception {
        //arrange

        var page = new Issue3029KeepingMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.choiceTypeIssue("//div[text()='Хранение с вложением в хранилище']");
        page.insertFromDate();
        page.choicePointSender("//div[text()='03149 - Точка инкассации клиента Несвиж']");
        page.insertToDate();
        page.choicePointRecipient("//div[text()='03152 - УХ Альфа-Банк']");
        page.textArea.sendKeys("Создана автотестом!");
        page.addAttachment();
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

