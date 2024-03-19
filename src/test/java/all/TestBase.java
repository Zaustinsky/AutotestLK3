package all;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestBase {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public String initialWindow;

    public Set<String> getAllWindows(){ //метод получения всех открытых окон/вкладок
        return driver.getWindowHandles();
    }
    public void switchToFirstNewWindow(){
        var otherWindows= driver.getWindowHandles().stream().filter(w -> !w.equals(initialWindow)).collect(Collectors.toSet()); //получение других открытых окон (не текущего)
        driver.switchTo().window(otherWindows.stream().findFirst().get()); // переход в первое окно браузера, отличающееся от первоначального
    }

    public void switchToWindow(String windowId){
        driver.switchTo().window(windowId); //переход в активное окно по его идентификатору

    }

    @BeforeEach
    public void setUp() {
//        System.setProperty("webdriver.gecko.driver", "C:\\SeleniumGecko\\geckodriver.exe");
//        driver = new FirefoxDriver();
//        new WebDriverWait(driver, 5);
//        wait = new WebDriverWait(driver, 10);
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver122.exe");
        var options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications"); // отключение всяких ругательных уведомлений браузера
        options.setAcceptInsecureCerts(true); //Использование метода setAcceptInsecureCerts с параметром true для принятия ненадежного сертификата (для браузера Хром)
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE); //настройка опции по игнорированию селениумом аллерта
        driver = new ChromeDriver(options);
        initialWindow = driver.getWindowHandle(); //получение текущего окна браузера
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() throws IOException { //метод обработки ошибки взаимодействия selenium с alert при выполнении скриншота (решение: выводим текст alert в консоль, после чего, закрываем alert и делаем скриншот)
        try{
            takeScreenshot();
        } catch (UnhandledAlertException alertException){
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());

            takeScreenshot();
        }
    }
    private void takeScreenshot() throws IOException{
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("screenshot.png"));
        driver.quit();
    }
}
