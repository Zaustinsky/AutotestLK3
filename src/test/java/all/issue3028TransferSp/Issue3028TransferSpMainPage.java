package all.issue3028TransferSp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Issue3028TransferSpMainPage {

    public WebDriver driver;
    public WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public Issue3028TransferSpMainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public final static String delimiter = "---------------------------------------------------";

    @FindBy(css = ".AuthorizationView__input")
    public WebElement login;

    @FindBy(id = "_inputTwoEl")
    public WebElement password;

    @FindBy(css = "button.toComeIn.authorization")
    public WebElement enter;

    @FindBy(css = "#details-button")
    public WebElement additionally;

    @FindBy(css = "#proceed-link")
    public WebElement transition;

    @FindBy(xpath = "//a[@href='#/outbank/requests/transfers/transfer-sp']")
    public WebElement issueTransferSp;

    @FindBy(css = ".svg-add")
    public WebElement scrollToPlus;

    @FindBy(css = ".svg-add-2")
    public WebElement buttonAddForm;

    @FindBy(xpath = "//label[@for='action']/parent::div[@class='itemGroup']//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement typeIssue;

    @FindBy(xpath = "//label[@for='payer']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement fieldBic;

    @FindBy(xpath = "//label[@for='sender']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement fieldBankSender;

    @FindBy(xpath = "//label[@for='senderUnitId']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement fieldDivisionSender;

    @FindBy(xpath = "//label[@for='senderPointId']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement fieldPointSender;

    @FindBy(css = "#actionDate")
    public WebElement inputActionDate;

    @FindBy(xpath = "//label[@for='recipient']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement fieldBankRecipient;

    @FindBy(xpath = "//label[@for='recipientUnitId']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement fieldDivisionRecipient;

    @FindBy(xpath = "//label[@for='recipientPointId']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement fieldPointRecipient;

    @FindBy(css = "#boxNumber")
    public WebElement boxNumber;

    @FindBy(css = "#press")
    public WebElement pressNumber;

    @FindBy(css = "._w-500")
    public WebElement textArea;

    @FindBy(css = ".addNotif")
    public WebElement addIssue;

    @FindBy(xpath = "//div[@class='checkboxInput__wrapper'][1]//div[@class='item-itemTable _widthCell-10-100'][5]//span[@class='item-itemTable__label']")
    public WebElement status;

    @FindBy(css = ".event_block__item_content")
    public WebElement signPopup;


    public void open() {
        driver.navigate().to("https://192.168.51.29:8243/ndo/outbank/1.0/user");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        additionally.click();
        transition.click();
        driver.navigate().to("https://big.lwo.by/auth");
        login.sendKeys("outbank");
        password.sendKeys("123456");
        enter.click();

    }

    public void getSummRows() {
        //WebDriver driver = DriverFactory.getWebDriver();
        List<WebElement> optionsList = driver.findElements(By.xpath("//div[@class='tableListItems_group_wrapper']/*"));
        int countDown = 0;
        for (WebElement span : optionsList) {
            if (span.getAttribute("class").startsWith("checkboxInput__wrapper")) {
                countDown++;

            }
        }
        System.out.println("Количество созданных заявок: " + countDown); //отбор нужного количества строк с одинаковым значением атрибута class + получение их общей суммы
        System.out.println(delimiter);
    }

    public void issue() { //вкладка подзаявки из вкладки "Заявки"
        issueTransferSp.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void scrollToButtonAddFormIssue(){
        new Actions(driver)
                .moveToElement(scrollToPlus)
                .moveToElement(buttonAddForm)
                .click()
                .perform();
    }

    public void choiceBic(String xpath){
        Actions actions = new Actions(driver);
        actions.moveToElement(fieldBic).click().build().perform();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }

    public void choiceBankSender(String xpath){
        Actions actions = new Actions(driver);
        actions.moveToElement(fieldBankSender).click().build().perform();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }

    public void choiceDivisionSender(String xpath){
        Actions actions = new Actions(driver);
        actions.moveToElement(fieldDivisionSender).click().build().perform();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }

    public void choicePointSender(String xpath){
        Actions actions = new Actions(driver);
        actions.moveToElement(fieldPointSender).click().build().perform();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }

    public void choiceBankRecipient(String xpath){
        Actions actions = new Actions(driver);
        actions.moveToElement(fieldBankRecipient).click().build().perform();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }

    public void choiceDivisionRecipient(String xpath){
        new Actions(driver)
                 .moveToElement(addIssue)
                 .moveToElement(fieldDivisionRecipient)
                 .click()
                 .perform();

        fieldDivisionRecipient.click();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }
    public void choicePointRecipient(String xpath){
        Actions actions = new Actions(driver);
        actions.moveToElement(fieldPointRecipient).click().build().perform();
        fieldPointRecipient.click();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }

    public void insertActionDate() { //выбор даты
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String output = dateFormat.format(date);
        inputActionDate.sendKeys(output);
    }

    public void addAttachment() {
        WebElement motion = driver.findElement(By.xpath("//*[text()='Добавить']")); //поиск кнопки "Добавить"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", motion); //скрол вниз на невидимый элемент
        motion.click();
        driver.findElement(By.xpath("//*[text()='Номер ценности (сумки)']/ancestor::div//input[@id='boxNumber']")).sendKeys("123"); //
        driver.findElement(By.xpath("//*[text()='Номер пломбира']/ancestor::div//input[@id='press']")).sendKeys("145");
        driver.findElement(By.xpath("//label[@for='box']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")).click();
        driver.findElement(By.xpath("//*[text()='Сумка']")).click();
        driver.findElement(By.xpath("//label[@for='currency']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")).click();
        driver.findElement(By.xpath("//*[text()='BYN']")).click();
        driver.findElement(By.xpath("//input[@id='sum']")).sendKeys("1000");
        driver.findElement(By.xpath("//input[@id='equivalent']")).sendKeys("1000");
        driver.findElement(By.xpath("//*[text()='Добавить ценность']")).click(); //кнопка "Добавить ценность";
        driver.findElement(By.xpath("//*[text()='Добавить заявку']")).click(); //кнопка "Добавить заявку"
    }

    public void scribeIssue() throws Exception { // подписание заявки
        driver.findElement(By.xpath("//div[@id='root']/div/header/div[3]/div[2]/div/div/div[3]/button")).click(); //очистить
        driver.findElement(By.xpath("//div[@class='wrapper-drop']")).click(); //клик по менюшке для вызова меню подписания
        driver.findElement(By.xpath("//a[@class='itemActionDrop'][3]/span")).click(); //п.м. "Подписать"
        driver.findElement(By.xpath("//div[@id='root']/div/div[2]/div/div/div/div[2]/div[4]/button[2]")).click(); //кнопка "Подписать" на форме подписания
        driver.findElement(By.xpath("(//input[@name='signPassword'])[2]")).sendKeys("11111111"); //ввод пароля
        driver.findElement(By.xpath("//*[text() = 'Подписать']")).click(); // п.м. "Подписать"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebElement result = driver.findElement(By.xpath("//div[@class='m-body bulkSignature']//div[@class='TextPopupWrapper'][2]//span[@class='f-s-HL-14']"));
        boolean Exception = false;

        if (result.getText().startsWith("Подписано: 1 документов")) {
            driver.findElement(By.xpath("//div[@id='root']/div/div[2]/div/div/div/div[3]/button")).click(); //закрыть форму уведомления о подписании
            //driver.findElement(By.xpath("//div[@id='root']/div/header/div[3]/div[2]/div/div/div[3]/button")).click(); //ок
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } else {
            throw new Exception("Не удалось подписать заявку! Проверьте корректность ввода данных!");

        }
    }
    public void getWatchIssues() { // раскрытие и просмотр созданной заявки
        driver.findElement(By.xpath("//div[@class='item-itemTable _widthCell-5-60']")).click();
        WebElement number = driver.findElement(By.xpath("//div[@class='item-itemTable _widthCell-5-103']/span[@class='item-itemTable__label']"));
        System.out.println("ID созданной заявки: " + number.getText());
        System.out.println(delimiter);
    }
    public void parseRowsIssue() {
        //парсинг строки созданной заявки (вывод соответствия полей: наименование и значение)
        List<WebElement> headerColumns = driver.findElements(By.xpath("//div[@class='contentHeader_fixed']//div[@class='headerTable _h-60'][1]/*"));
        List<WebElement> listOfRows = driver.findElements(By.xpath("//div[@class='tableItem ']/*"));
        for (int i = 0; i < headerColumns.size(); i++) {
            System.out.println(headerColumns.get(i).getText() + ": " + listOfRows.get(i).getText());
        }
        System.out.println(delimiter);
    }
    public void parseRowsIssueAttachments() {
        //парсинг строки вложений созданной заявки (вывод соответствия полей: наименование и значение)
        List<WebElement> headerColumns = driver.findElements(By.xpath("//div[@class='tableC nTable']//div[@class='headerTable _h-60']/*"));
        List<WebElement> listOfRows = driver.findElements(By.xpath("//div[@class='tableC nTable']//div[@class='contentTable ']/div[@class='wrapper-itemTable undefined']/div[@class='tableItem ']"));
        for (WebElement row : listOfRows) {
            List<WebElement> childs = row.findElements(By.xpath("./*"));
            for (int i = 0; i < headerColumns.size(); i++) {
                System.out.println(headerColumns.get(i).getText() + ": " + childs.get(i).getText());
            }
            System.out.println(delimiter);
        }
    }
}
