package all.issue3030BetweenBanks;

import all.TestBase;
import all.issue3029Keeping.Issue3029KeepingMainPage;
import all.issue3030BetweenBanks.Issue3030BetweenBanksMainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Issue3030BetweenBanksTest extends TestBase {

    @Test
    public void test3030BetweenBanks() throws Exception {
        //arrange

        var page = new Issue3030BetweenBanksMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.elementToBeClickable(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.insertActionDate();
        page.choiceBic("//div[text()=\"ALFABY2X - Г.MИHCK, ЗAO 'Альфа-Банк'\"]");
        page.choiceBankSender("//div[text()=\"ALFABY2X-Г.MИHCK, ЗAO 'Альфа-Банк'\"]");
        page.choiceDivisionSender("//div[text()='9 - 9 Центральный аппарат Дзержинск']");
        page.choicePointSender("//div[text()='1332 - Касса структурногго подразделения']");
        page.choiceBankRecipient("//div[text()=\"AKBBBY2X-Г.MИHСK, ОАО 'AСБ БЕЛАРУСБАНК'\"]");
        wait.until(ExpectedConditions.elementToBeClickable(page.fieldDivisionRecipient));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        page.choiceDivisionRecipient("//div[text()='11111111 - Филиал №111111 Беларусбанк']");
        page.choicePointRecipient("//div[text()='08100 - Касса']");
        page.addAttachment();
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

