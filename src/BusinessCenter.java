public class BusinessCenter {
    int liftFloor; // на каком этаже
    Visitor visitorAtControl;
    Visitor visitorInLift;
    boolean liftFree;

    public BusinessCenter() {
    }

    public boolean enterControl(Visitor v) {
        /*
        •	Код ожидания освобождения проходной (сначала можно пропустить)
        •	Занятие проходной (занесение ссылки v на посетителя в соответствующее поле visitorAtControl)
        •	Отладочную печать на всех этапах (см. с. 4)

        */
        return  false;
    }
    public void  passControl(Visitor v) {
        /*
        •	Контрольная проверка, что проходная свободна (эта проверка всегда должна проходить при отсутствии ошибок в программе, так что можно в противном случае выбросить исключение)
        •	Thread.sleep для имитации задержки (в реальном приложении там может быть проверка паспорта клиента по базе данных, например)
        •	Код освобождения проходной для последующих посетителей
        •	Отладочная печать на всех этапах

         */


    }
public boolean callLift (Visitor v) {
    /*
     Функция callLiftAndWait возвращает true не когда лифт приехал, а когда он лифт готов ехать на этаж к посетителю (пустой!), т.е. «зарезервирован»
     */

    return  false;

}

public void moveLift (Visitor v, int targetFloor){
        /*
        Задержка в методе moveLift зависит от расстояния между этажами liftFloor и targetFloor, а поле liftFloor в результате вызова moveLift изменяется
         */

}

public void enterLift (Visitor v) {

}

public void exitLift() {

}




}