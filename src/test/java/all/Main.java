package all;

public class Main extends TestBase{

    @org.junit.jupiter.api.Test
    public void test() throws Exception {
        Test test = new Test();
        //УС перевозка
        test.test3025TransferUsIncass(); //инкассация
        test.test3025TransferUsLoad(); //загрузка
        test.test3025TransferUsUnload(); //выгрузка
        //УС аутсорсинг
        test.test3026OutsourcingUsIncass(); //инкассация
        test.test3026OutsourcingUsLoad(); //загрузка
        test.test3026OutsourcingUsUnload(); //выгрузка
//        Обслуживание СП
        test.test3027ServiceSpIncass(); //инкассация
        test.test3027ServiceSpLoad(); //Подкрепление
        test.test3027ServiceSpUnload(); //Вывоз
//        Перевозка между СП
        test.test3028TransferSp();
        //Хранение
        test.test3029KeepingInStorage(); //с вложением в хранилище
        test.test3029KeepingTestOutStorage(); //изъятие из хранилища
        test.test3029SafeKeeping(); //хранение под ответственностью с перевозкой
        //Операции между банками
        test.test3030BetweenBanks();
    }
}
