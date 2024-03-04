package all;

import all.issue3025TransferUs.Issue3025TransferUsMainPage;
import all.issue3026OutsourcingUs.Issue3026OutsourcingUsMainPage;
import all.issue3027ServiceSp.Issue3027ServiceSpMainPage;
import all.issue3028TransferSp.Issue3028TransferSpMainPage;
import all.issue3029Keeping.Issue3029KeepingMainPage;
import all.issue3030BetweenBanks.Issue3030BetweenBanksMainPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Test extends TestBase{


    @org.junit.jupiter.api.Test
    public void test3025TransferUsLoad() throws Exception { //заявка на перевозку между банками
        //arrange
        var page = new Issue3025TransferUsMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.insertFromDate();
        page.choicePointBase("//div[text()='03127 - Точка инкассации клиента Несвиж']");
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

    @org.junit.jupiter.api.Test
    public void test3025TransferUsIncass() throws Exception { //заявка УС перевозка (инкассация)
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
        page.choicePointBase("//div[text()='03127 - Точка инкассации клиента Несвиж']");
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

    @org.junit.jupiter.api.Test
    public void test3025TransferUsUnload() throws Exception { //заявка УС перевозка (выгрузка)
        //arrange
        var page = new Issue3025TransferUsMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Выгрузка']");
        page.insertActionDate();
        page.choiceSst("//div[text()='1234-Минская обл., Несвижский р-н., г. Несвиж, ул. Сосновая, 78']");
        page.insertDeliveryDate();
        page.choicePointBase("//div[text()='03127 - Точка инкассации клиента Несвиж']");
        page.textArea.sendKeys("создана автотестом!");
        page.addIssue.click();
        page.scribeIssue();
        page.getWatchIssues();
        page.getSummRows();
        page.parseRowsIssue();
        //assert
        var expectedResultSign = "Подпись прошла успешно";
        var expectedResult = "Подписано банком";

        Assertions.assertAll(
                ()-> Assertions.assertEquals(expectedResult, page.status.getText(), "Заявка не подписана"), //проверка на соответствие значения string статуса
                ()-> Assertions.assertEquals(expectedResultSign, page.signPopup.getText(), "Заявка не подписана") //проверка соответствия наименования об успешном подписания заявки в попапе
        );

        page.signPopup.click();
    }

    @org.junit.jupiter.api.Test
    public void test3026OutsourcingUsLoad() throws Exception { //заявка УС Аутсорсинг (загрузка)
        //arrange
        var page = new Issue3026OutsourcingUsMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.issueOutsourcing.click();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
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

    @org.junit.jupiter.api.Test
    public void test3026OutsourcingUsIncass() throws Exception { //заявка УС Аутсорсинг (инкассация)
        //arrange
        var page = new Issue3026OutsourcingUsMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.issueOutsourcing.click();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Инкассация']");
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

    @org.junit.jupiter.api.Test
    public void test3026OutsourcingUsUnload() throws Exception { //заявка УС Аутсорсинг (выгрузка)
        //arrange
        var page = new Issue3026OutsourcingUsMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.issueOutsourcing.click();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Выгрузка']");
        page.insertActionDate();
        page.choiceSst("//div[text()='1234-Минская обл., Несвижский р-н., г. Несвиж, ул. Сосновая, 78']");
        page.textArea.sendKeys("создана автотестом!");
        page.addIssue.click();
        page.scribeIssue();
        page.getWatchIssues();
        page.getSummRows();
        page.parseRowsIssue();
        //assert
        var expectedResultSign = "Подпись прошла успешно";
        var expectedResult = "Подписано банком";

        Assertions.assertAll(
                ()-> Assertions.assertEquals(expectedResult, page.status.getText(), "Заявка не подписана"), //проверка на соответствие значения string статуса
                ()-> Assertions.assertEquals(expectedResultSign, page.signPopup.getText(), "Заявка не подписана") //проверка соответствия наименования об успешном подписания заявки в попапе
        );

        page.signPopup.click();
    }

    @org.junit.jupiter.api.Test
    public void test3027ServiceSpLoad() throws Exception { //заявка Обслуживание СП (загрузка)
        //arrange
        var page = new Issue3027ServiceSpMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Подкрепление']");
        page.insertActionDate();
        page.choicePoint("//div[text()='03127 - Точка инкассации клиента Несвиж']");
        page.textArea.sendKeys("создана автотестом!");
        page.addAttachment();
        page.addIssue.click();
        page.scribeIssue();
        page.getWatchIssues();
        page.getSummRows();
        page.parseRowsIssue();
        page.parseRowsIssueAttachments();
        //assert
        var expectedResultSign = "Подпись прошла успешно";
        var expectedResult = "Подписано Банком";

        Assertions.assertAll(
                ()-> Assertions.assertEquals(expectedResult, page.status.getText(), "Заявка не подписана"), //проверка на соответствие значения string статуса
                ()-> Assertions.assertEquals(expectedResultSign, page.signPopup.getText(), "Заявка не подписана") //проверка соответствия наименования об успешном подписания заявки в попапе
        );

        page.signPopup.click();
    }

    @org.junit.jupiter.api.Test
    public void test3027ServiceSpIncass() throws Exception { //заявка Обслуживание СП (инкассация)
        //arrange

        var page = new Issue3027ServiceSpMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Инкассация']");
        page.insertActionDate();
        page.choicePoint("//div[text()='03127 - Точка инкассации клиента Несвиж']");
        page.textArea.sendKeys("создана автотестом!");
        page.addAttachment();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page.addAttachmentIncass();
        page.addIssue.click();
        page.scribeIssue();
        page.getWatchIssues();
        page.getSummRows();
        page.parseRowsIssue();
//        page.parseRowsIssueAttachments();
        //assert
        var expectedResultSign = "Подпись прошла успешно";
        var expectedResult = "Подписано Банком";

        Assertions.assertAll(
                ()-> Assertions.assertEquals(expectedResult, page.status.getText(), "Заявка не подписана"), //проверка на соответствие значения string статуса
                ()-> Assertions.assertEquals(expectedResultSign, page.signPopup.getText(), "Заявка не подписана") //проверка соответствия наименования об успешном подписания заявки в попапе
        );

        page.signPopup.click();
    }

    @org.junit.jupiter.api.Test
    public void test3027ServiceSpUnload() throws Exception { //заявка Обслуживание СП (выгрузка)
        //arrange

        var page = new Issue3027ServiceSpMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.typeIssue("//div[text()='Вывоз']");
        page.insertActionDate();
        page.choicePoint("//div[text()='03127 - Точка инкассации клиента Несвиж']");
        page.textArea.sendKeys("создана автотестом!");
        page.addAttachmentVyvoz();
        page.addIssue.click();
        page.scribeIssue();
        page.getWatchIssues();
        page.getSummRows();
        page.parseRowsIssue();
        page.parseRowsIssueAttachments();
        //assert
        var expectedResultSign = "Подпись прошла успешно";
        var expectedResult = "Подписано Банком";

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResult, page.status.getText(), "Заявка не подписана"), //проверка на соответствие значения string статуса
                () -> Assertions.assertEquals(expectedResultSign, page.signPopup.getText(), "Заявка не подписана") //проверка соответствия наименования об успешном подписания заявки в попапе
        );

        page.signPopup.click();
    }

    @org.junit.jupiter.api.Test
    public void test3028TransferSp() throws Exception { //заявка на перевозку между СП
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

    @org.junit.jupiter.api.Test
    public void test3029KeepingInStorage() throws Exception { //заявка хранение (с вложением в УХ)
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
        page.choicePointSender("//div[text()='03127 - Точка инкассации клиента Несвиж']");
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

    @org.junit.jupiter.api.Test
    public void test3029KeepingTestOutStorage() throws Exception { //заявка хранение (ихъятие из хранилища)
        //arrange

        var page = new Issue3029KeepingMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.choiceTypeIssue("//div[text()='Изъятие ценностей, ранее принятых на хранение']");
        page.insertFromDate();
        page.choicePointSender("//div[text()='03127 - Точка инкассации клиента Несвиж']");
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

    @org.junit.jupiter.api.Test
    public void test3029SafeKeeping() throws Exception { //заявка хранение (под ответственностью, с перевозкой)
        //arrange

        var page = new Issue3029KeepingMainPage(driver, wait);
        page.open();
        //act
        page.issue();
        page.getSummRows();
        wait.until(ExpectedConditions.visibilityOf(page.scrollToPlus));
        page.scrollToButtonAddFormIssue();
        page.choiceTypeIssue("//div[text()='Хранение под ответственностью с перевозкой']");
        page.insertFromDate();
        page.choicePointSender("//div[text()='03127 - Точка инкассации клиента Несвиж']");
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

    @org.junit.jupiter.api.Test
    public void test3030BetweenBanks() throws Exception { //заявка на операции между банками
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

