package all.issue3027ServiceSp;

import all.TestBase;
import all.issue3027ServiceSp.Issue3027ServiceSpMainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Issue3027ServiceSpTest extends TestBase {

    @Test
    public void test3027ServiceSp() throws Exception {
        //arrange

        var page = new Issue3027ServiceSpMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
//        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Подкрепление']");
        page.insertActionDate();
        page.choicePoint("//div[text()='03149 - Точка инкассации клиента Несвиж']");
        page.textArea.sendKeys("создана автотестом!");
        page.addAttachment();
        page.scribeIssue();
        page.getWatchIssues();
        page.parseRowsIssue();
        page.parseRowsIssueAttachments();
        page.getSummRows();
        //assert
        var expectedResultSign = "Подпись прошла успешно";
        var expectedResult = "Подписано Банком";

        Assertions.assertAll(
                ()-> Assertions.assertEquals(expectedResult, page.status.getText(), "Заявка не подписана"), //проверка на соответствие значения string статуса
                ()-> Assertions.assertEquals(expectedResultSign, page.signPopup.getText(), "Заявка не подписана") //проверка соответствия наименования об успешном подписания заявки в попапе
        );

        page.signPopup.click();
    }

}
