package all.issue3025TransferUs;

import org.openqa.selenium.*;
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

    public class Issue3025TransferUsMainPage {
        public WebDriver driver;
        public WebDriverWait wait;
        private JavascriptExecutor jsExecutor;

        public Issue3025TransferUsMainPage(WebDriver driver, WebDriverWait wait) {
            this.driver = driver;
            this.wait = wait;
            PageFactory.initElements(driver, this);
        }

        public final static String delimiter = "---------------------------------------------------";

        @FindBy(css = ".ignorelink")
        public WebElement acceptRisk;

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

        @FindBy(xpath = "//a[@href='#/outbank/requests/request-self-service-devices']")
        public WebElement issue;

        @FindBy(css = ".svg-add")
        public WebElement scrollToPlus;

        @FindBy(css = ".svg-add-2")
        public WebElement buttonAddForm;

        @FindBy(xpath = "//label[@for='action']/parent::div[@class='itemGroup']//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
        public WebElement typeIssue;

        @FindBy(css = "#fromDate")
        public WebElement inputFromDate;

        @FindBy(css = "#deliveryDate")
        public WebElement inputDeliveryDate;

        @FindBy(xpath = "//label[@for='pointBaseId']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
        public WebElement pointBase;

        @FindBy(css = "#actionDate")
        public WebElement inputActionDate;

        @FindBy(xpath = "//label[@for='sstId']/parent::div//div[@class='css-1rtrksz select__value-container select__value-container--has-value']")
        public WebElement sst;

        @FindBy(css = "._w-500")
        public WebElement textArea;

        @FindBy(css = ".addNotif")
        public WebElement addIssue;

        @FindBy(xpath = "//div[@class='checkboxInput__wrapper'][1]//div[@class='item-itemTable _widthCell-10-100']//span[@class='item-itemTable__label']")
        public WebElement status;

        @FindBy(css = ".event_block__item_content")
        public WebElement signPopup;

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
        public void issue() { //вкладка "Заявки"
            issue.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }

        public void scrollToButtonAddFormIssue(){
            new Actions(driver)
                    .moveToElement(scrollToPlus)
                    .moveToElement(buttonAddForm)
                    .click()
                    .perform();
        }

        public void typeIssue(String xpath){
            Actions actions = new Actions(driver);
            actions.moveToElement(typeIssue).click().build().perform();
            WebElement device = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
        }

        public void insertFromDate() { //выбор даты
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String output = dateFormat.format(date);
            inputFromDate.sendKeys(output);
        }
        public void choicePointBase(String xpath){
            Actions actions = new Actions(driver);
            actions.moveToElement(pointBase).click().build().perform();
            WebElement device = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
        }
        public void insertActionDate() { //выбор даты
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String output = dateFormat.format(date);
            inputActionDate.sendKeys(output);
        }
        public void choiceSst(String xpath){
            Actions actions = new Actions(driver);
            actions.moveToElement(sst).click().build().perform();
            WebElement device = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", device); //JS скрипт клик элемента
        }
        public void insertDeliveryDate() { //выбор даты
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String output = dateFormat.format(date);
            inputDeliveryDate.sendKeys(output);
        }
        public void mouseOverToCassette() { //заполнение кассет ДН
            WebElement motion1 = driver.findElement(By.xpath("//div[@class='itemActionBlock']")); //кассета 1
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", motion1); //скрол вниз на невидимый элемент
            motion1.click();
            motion1.click();
            driver.findElement(By.xpath("//input[@id='amount']")).sendKeys("1000");
            driver.findElement(By.cssSelector("._button2")).click();

            WebElement motion2 = driver.findElement(By.xpath("//div[@class='wrapper-itemTable undefined'][2]//div[@class='itemActionBlock']")); //кассета 2
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", motion2); //скрол вниз на невидимый элемент
            motion2.click();
            motion2.click();
            driver.findElement(By.xpath("//input[@id='amount']")).sendKeys("1000");
            driver.findElement(By.cssSelector("._button2")).click();

            WebElement motion3 = driver.findElement(By.xpath("//div[@class='wrapper-itemTable undefined'][3]//div[@class='itemActionBlock']")); //кассета 3
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", motion3); //скрол вниз на невидимый элемент
            motion3.click();
            motion3.click();
            driver.findElement(By.xpath("//input[@id='amount']")).sendKeys("1000");
            driver.findElement(By.cssSelector("._button2")).click();
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

