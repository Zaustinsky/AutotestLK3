package all.issue3028TransferSp;

import all.TestBase;
import all.issue3028TransferSp.Issue3028TransferSpMainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Issue3028TransferSpTest extends TestBase {

    @Test
    public void test3028TransferSp() throws Exception {
        //arrange

        var page = new Issue3028TransferSpMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.choiceBic("//div[text()=\"ALFABY2X - Г.MИHCK, ЗAO 'Альфа-Банк'\"]");
        page.choiceBankSender("//div[text()=\"ALFABY2X-Г.MИHCK, ЗAO 'Альфа-Банк'\"]");
        page.choiceDivisionSender("//div[text()='9 - 9 Центральный аппарат Дзержинск']");
        page.choicePointSender("//div[text()='1332 - Касса структурногго подразделения']");
        page.insertActionDate();
        page.choiceBankRecipient("//div[text()=\"ALFABY2X - Г.MИHCK, ЗAO 'Альфа-Банк'\"]");
        wait.until(ExpectedConditions.elementToBeClickable(page.fieldDivisionRecipient));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        page.choiceDivisionRecipient("//div[text()='9 - 9 Центральный аппарат Дзержинск']");
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
