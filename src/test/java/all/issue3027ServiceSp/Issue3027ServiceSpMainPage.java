package all.issue3027ServiceSp;

import io.qameta.allure.Step;
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

public class Issue3027ServiceSpMainPage {

    public WebDriver driver;
    public WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public Issue3027ServiceSpMainPage(WebDriver driver, WebDriverWait wait) {
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

    @FindBy(xpath = "//a[@href='#/outbank/requests/service-sp']")
    public WebElement issueServiceSp;

    @FindBy(css = ".svg-add")
    public WebElement scrollToPlus;

    @FindBy(css = ".svg-add-2")
    public WebElement buttonAddForm;

    @FindBy(xpath = "//label[@for='action']/parent::div[@class='itemGroup']//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement typeIssue;

    @FindBy(css = "#actionDate")
    public WebElement inputActionDate;

    @FindBy(xpath = "//label[@for='pointId']/parent::div[@class='itemGroup']//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
    public WebElement pointId;

    @FindBy(css = "._w-500")
    public WebElement textArea;

    @FindBy(css = ".addNotif")
    public WebElement addIssue;

    @FindBy(xpath = "//div[@class='checkboxInput__wrapper'][1]//div[@class='item-itemTable _widthCell-10-100'][5]//span[@class='item-itemTable__label']")
    public WebElement status;

    @FindBy(css = ".event_block__item_content")
    public WebElement signPopup;

    @FindBy(xpath = "//div[@class = 'group-wrapper wrapper_separator'][3]//*[text()='Добавить']")
    public WebElement addAttachmentVyvozButton;

    @Step
    public void open() {
        driver.navigate().to("https://big.lwo.by/auth");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        login.sendKeys("outbank");
        password.sendKeys("123456");
        enter.click();
    }

    // старый метод открытия страницы с неработающими сертификатами от Касперского (стал не нужным, когда: 1) добавил в TestBase исключение для всплывающих уведомлений (сертификатов) "--disable-notifications" и 2) добавил в исключения в настройки антивируса Касперского сайт big.lwo.by)
//    public void open() {
//        driver.navigate().to("https://192.168.51.29:8243/ndo/outbank/1.0/user");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        additionally.click();
//        transition.click();
//        driver.navigate().to("https://big.lwo.by/auth");
//        login.sendKeys("outbank");
//        password.sendKeys("123456");
//        enter.click();
//
//    }

    @Step
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

    @Step
    public void issue() { //вкладка подзаявки из вкладки "Заявки"
        issueServiceSp.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Step
    public void scrollToButtonAddFormIssue(){
        new Actions(driver)
                .moveToElement(scrollToPlus)
                .moveToElement(buttonAddForm)
                .click()
                .perform();
    }

    @Step
    public void typeIssue(String xpath){
        Actions actions = new Actions(driver);
        actions.moveToElement(typeIssue).click().build().perform();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }

    @Step
    public void insertActionDate() { //выбор даты
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String output = dateFormat.format(date);
        inputActionDate.sendKeys(output);
    }

    @Step
    public void choicePoint(String xpath){
        Actions actions = new Actions(driver);
        actions.moveToElement(pointId).click().build().perform();
        WebElement device = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
    }

    @Step
    public void addAttachment() {
        WebElement motion = driver.findElement(By.xpath("//*[text()='Добавить']")); //поиск кнопки "Добавить"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", motion); //скрол вниз на невидимый элемент
        motion.click();
        driver.findElement(By.xpath("//*[text()='Номинал валюты']/ancestor::div[@class='itemGroup']")).click(); //xpath к полю "номинал валюты";
        driver.findElement(By.xpath("//*[text()='100']")).click(); //ввод номинала валюты
        driver.findElement(By.xpath("//input[@id='amount']")).sendKeys("1000");
        driver.findElement(By.xpath("//*[text()='Добавить Вложение']")).click(); //кнопка "Добавить вложение";
    }

    @Step
    public void addAttachmentIncass() {
        WebElement motion = driver.findElement(By.xpath("//div[@class = 'group-wrapper wrapper_separator'][3]//*[text()='Добавить']")); //поиск кнопки "Добавить"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", motion); //скрол вниз на невидимый элемент
        motion.click();
        driver.findElement(By.xpath("//*[text()='Валюта']/ancestor::div[@class='itemGroup']//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")).click(); //xpath к полю "номинал валюты";
//        driver.findElement(By.xpath("//*[text()='BYN']")).click(); //ввод номинала валюты
        driver.findElement(By.xpath("//input[@id='sum']")).sendKeys("150000");
        driver.findElement(By.xpath("//*[text()='Добавить Вложение']")).click(); //кнопка "Добавить вложение";
    }

    @Step
    public void addAttachmentVyvoz() {
        WebElement motion = driver.findElement(By.xpath("//*[text()='Добавить']")); //поиск кнопки "Добавить"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", motion); //скрол вниз на невидимый элемент
        motion.click();
        driver.findElement(By.xpath("//*[text()='Валюта']/ancestor::div[@class='itemGroup']//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")).click(); //xpath к полю "номинал валюты";
//        driver.findElement(By.xpath("//*[text()='BYN']")).click(); //ввод номинала валюты
        driver.findElement(By.xpath("//input[@id='sum']")).sendKeys("150000");
        driver.findElement(By.xpath("//*[text()='Добавить Вложение']")).click(); //кнопка "Добавить вложение";
    }

    @Step
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

    @Step
    public void getWatchIssues() { // раскрытие и просмотр созданной заявки
        driver.findElement(By.xpath("//div[@class='item-itemTable _widthCell-5-60']")).click();
        WebElement number = driver.findElement(By.xpath("//div[@class='item-itemTable _widthCell-5-103']/span[@class='item-itemTable__label']"));
        System.out.println("ID созданной заявки: " + number.getText());
        System.out.println(delimiter);
    }

    @Step
    public void parseRowsIssue() {
        //парсинг строки созданной заявки (вывод соответствия полей: наименование и значение)
        List<WebElement> headerColumns = driver.findElements(By.xpath("//div[@class='contentHeader_fixed']//div[@class='headerTable _h-60'][1]/*"));
        List<WebElement> listOfRows = driver.findElements(By.xpath("//div[@class='tableItem ']/*"));
        for (int i = 0; i < headerColumns.size(); i++) {
            System.out.println(headerColumns.get(i).getText() + ": " + listOfRows.get(i).getText());
        }
        System.out.println(delimiter);
    }

    @Step
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
