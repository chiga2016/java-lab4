public class BusinessCenter {
    int liftFloor; // на каком этаже
    Visitor visitorAtControl; //какой посетитель занял проходную если null то проходная пуста
    Visitor visitorInLift; // какой посетитель занял лифт если null то лифт свободен
    boolean liftFree; // флажок лифт занят или нет

    public BusinessCenter() {
        setLiftFloor(1);
        setLiftFree(true);
        setVisitorAtControl(null);
        setVisitorInLift(null);
    }

    public int getLiftFloor() {
        return liftFloor;
    }

    public void setLiftFloor(int liftFloor) {
        this.liftFloor = liftFloor;
    }

    public void setLiftFree(boolean liftFree) {
        this.liftFree = liftFree;
    }
    public boolean getLiftFree() {
        return liftFree;
    }

    public Visitor getVisitorAtControl() {
        return visitorAtControl;
    }
    public void setVisitorAtControl(Visitor visitorAtControl) {
        this.visitorAtControl = visitorAtControl;
    }

    public Visitor getVisitorInLift() {
        return visitorInLift;
    }

    public void setVisitorInLift(Visitor visitorInLift) {
        this.visitorInLift = visitorInLift;
    }

    public boolean enterControl(Visitor v) throws InterruptedException {
        /*
        •	Код ожидания освобождения проходной (сначала можно пропустить)
        •	Занятие проходной (занесение ссылки v на посетителя в соответствующее поле visitorAtControl)
        •	Отладочную печать на всех этапах (см. с. 4)
        */

//        synchronized (this.getVisitorAtControl()){
//          //  if (this.getVisitorAtControl()==null) {
//                this.setVisitorAtControl(v);
                System.out.println("Посетитель " + v.getNum() + " занял проходную. Поток " + Thread.currentThread().getId());
//
                return true;
          //  }
          //  else {
           // this.getVisitorAtControl().wait();
          //  }
            //this.getVisitorAtControl().notifyAll();
          //ё  return false;

//        }


    }
    public void  passControl(Visitor v) throws InterruptedException {
        System.out.println("Дошли до контроля");
        /*
        •	Контрольная проверка, что проходная свободна (эта проверка всегда должна проходить при отсутствии ошибок в программе, так что можно в противном случае выбросить исключение)
        •	Thread.sleep для имитации задержки (в реальном приложении там может быть проверка паспорта клиента по базе данных, например)
        •	Код освобождения проходной для последующих посетителей
        •	Отладочная печать на всех этапах
         */
        System.out.println(v + " показывает документы. Поток " + Thread.currentThread().getId());
        Thread.sleep(100);
        System.out.println(v + " прошел проходную. Поток " + Thread.currentThread().getId());
        this.visitorAtControl = null;


//        synchronized (this){
//            boolean b = true;
//            while (b) {
//                if (this.getVisitorAtControl() != null){
//                    this.enterControl(v);
//                    System.out.println(v + " показывает документы");
//                    Thread.sleep(50);
//                    this.exitFromControl(v);
//                    System.out.println(this + " показал документы");
//                    Thread.sleep(100);
//                    b = false;
//                } else {
//                    this.wait();
//                }
//            }
//            this.notifyAll();
//        }


    }

public void exitFromControl(Visitor v){
    setVisitorAtControl(v);
}
public boolean callLift (Visitor v) {
    System.out.println(v + " вызвал лифт");
    if (getVisitorInLift() == null) {
        return true;
    }
        return  false;
}
public boolean callLiftAndWait(Visitor v){
        /*
     Функция callLiftAndWait возвращает true не когда лифт приехал, а когда он лифт готов ехать на этаж к посетителю (пустой!),
     т.е. «зарезервирован»
     */

return false;
}

public void moveLift (Visitor v) throws InterruptedException {
        this.setLiftFree(false);
        int targetFloor=Math.abs(v.getFloor() -this.liftFloor  );

        Thread.sleep(targetFloor);
//        this.setLiftFloor(v.getFloor());
//        v.setFloor(this.getLiftFloor());
        System.out.println("Лифт приехал на " + v.getFloor() + "этаж");
        /*
        Задержка в методе moveLift зависит от расстояния между этажами liftFloor и targetFloor, а поле liftFloor в результате вызова moveLift изменяется
         */
}

public void enterLift (Visitor v) {
    setLiftFree(false);
    setVisitorInLift(v);
        System.out.println(v.toString() + " вошел в лифт");
}

public void exitFromLift(Visitor v) {
    setLiftFree(true);
    setVisitorInLift(v);
    System.out.println(v.toString() + " вышел из лифта");
}




}