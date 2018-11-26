public class BusinessCenter {
    int liftFloor; // на каком этаже
    Visitor visitorAtControl;
    Visitor visitorInLift;
    boolean liftFree;

    public BusinessCenter() {
        this.liftFloor = 1;
        this.liftFree = true;



    }



    public boolean enterControl(Visitor v) throws InterruptedException {
        /*
        •	Код ожидания освобождения проходной (сначала можно пропустить)
        •	Занятие проходной (занесение ссылки v на посетителя в соответствующее поле visitorAtControl)
        •	Отладочную печать на всех этапах (см. с. 4)

        */
        if (this.visitorAtControl==null) {
            this.visitorAtControl = v;
            System.out.println("Посетитель " + v.getNum() + " занял проходную. Поток " + Thread.currentThread().getId());
            return true; }
            return false;


    }
    public void  passControl(Visitor v) throws InterruptedException {
        /*
        •	Контрольная проверка, что проходная свободна (эта проверка всегда должна проходить при отсутствии ошибок в программе, так что можно в противном случае выбросить исключение)
        •	Thread.sleep для имитации задержки (в реальном приложении там может быть проверка паспорта клиента по базе данных, например)
        •	Код освобождения проходной для последующих посетителей
        •	Отладочная печать на всех этапах

         */

        System.out.println("Посетитель " +v.getNum() + " показывает документы. Поток " + Thread.currentThread().getId());
        Thread.sleep(1000);
        System.out.println("Посетитель " +v.getNum() + " прошел проходную. Поток " + Thread.currentThread().getId());
        this.visitorAtControl = null;


    }
public boolean callLift (Visitor v) {
    /*
     Функция callLiftAndWait возвращает true не когда лифт приехал, а когда он лифт готов ехать на этаж к посетителю (пустой!), т.е. «зарезервирован»
     */
if (visitorInLift == null) {
    visitorInLift = v;
    return true;
}
    return  false;

}

public void moveLift (Visitor v, int targetFloor){
        v.getFloor();


        /*
        Задержка в методе moveLift зависит от расстояния между этажами liftFloor и targetFloor, а поле liftFloor в результате вызова moveLift изменяется
         */

}

public void enterLift (Visitor v) {

}

public void exitLift() {

}




}