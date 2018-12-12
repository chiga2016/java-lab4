public class BusinessCenter {
    int liftFloor; // на каком этаже
    Visitor visitorAtControl; //какой посетитель занял проходную если null то проходная пуста
    Visitor visitorInLift; // какой посетитель занял лифт если null то лифт свободен
    boolean liftFree=true; // флажок лифт занят или нет
    boolean controlFree = true; // флажок  проходная занята или нет
    final String blockLift="block";
    final String blockProhod="block";

    public BusinessCenter() {
        setLiftFloor(1);
        setLiftFree(true);
        setVisitorAtControl(null);
        setVisitorInLift(null);
    }

//    public void setBlockLift(String blockLift) {this.blockLift = blockLift;}
//    public String getBlockLift() {return blockLift;}
//
//    public void setBlockProhod(String blockProhod) {this.blockProhod = blockProhod;}
//    public String getBlockProhod() {return blockProhod;}

    public void setLiftFloor(int liftFloor) {
        this.liftFloor = liftFloor;
    }
    public int getLiftFloor() { return liftFloor; }

    public Visitor getVisitorAtControl() {        return visitorAtControl;    }
    public void setVisitorAtControl(Visitor visitorAtControl) {        this.visitorAtControl = visitorAtControl;    }

    public Visitor getVisitorInLift() {        return visitorInLift;    }
    public void setVisitorInLift(Visitor visitorInLift) {        this.visitorInLift = visitorInLift;    }

    public void setLiftFree(boolean liftFree) {
        this.liftFree = liftFree;
    }
    public boolean getLiftFree() { return liftFree;  }

    public void setControlFree(boolean controlFree) { this.controlFree = controlFree; }
    public boolean getControlFree() {return  controlFree;}


    public boolean enterControl(Visitor v) throws InterruptedException {
        System.out.println("Вход в проходную");
       // liftStatus();
        boolean ppp = getControlFree();
        synchronized (blockProhod) {
            while (!controlFree) {
                    blockProhod.wait();
                System.out.println("Сообщение посетителю " + v + " Проходная пока занята");
            }
        }
       // if (getControlFree()) {
            setVisitorAtControl(v);
            System.out.println("Посетитель " + v.getNum() + " занял проходную. Поток " + Thread.currentThread().getId());
            setControlFree(false);
       // } else {

            //System.exit(1);
      //  }
        //System.out.println("ppp= " + ppp);
        return ppp;
    }

    public void  passControl(Visitor v) throws InterruptedException {
        System.out.println("Дошли до контроля");
       // liftStatus();
        /*
        •	Контрольная проверка, что проходная свободна (эта проверка всегда должна проходить при отсутствии ошибок в программе, так что можно в противном случае выбросить исключение)
        •	Thread.sleep для имитации задержки (в реальном приложении там может быть проверка паспорта клиента по базе данных, например)
        •	Код освобождения проходной для последующих посетителей
        •	Отладочная печать на всех этапах
         */
       // synchronized (blockProhod) {
            System.out.println(v + " показывает документы. Поток " + Thread.currentThread().getId());
            //Thread.sleep(100);
            System.out.println(v + " прошел проходную. Поток " + Thread.currentThread().getId());
            exitFromControl(v);
       // }
    }

public void exitFromControl(Visitor v){
    System.out.println("Выход из проходной ");
    //liftStatus();
    setVisitorAtControl(null);
    setControlFree(true);
    synchronized (blockProhod) {
        blockProhod.notifyAll();
    }
    }
public boolean callLiftAndWait (Visitor v) throws InterruptedException {
//        /*
//     Функция callLiftAndWait возвращает true не когда лифт приехал, а когда он лифт готов ехать на этаж к посетителю (пустой!),
//     т.е. «зарезервирован»
//     */
    System.out.println("Вызов лифта");
   // liftStatus();
        synchronized (blockLift) {
            while (!getLiftFree()) {
                System.out.println("Лифт занят. Ждем");
                blockLift.wait();
            }
        }

    if (getLiftFree()) {
        System.out.println(v + " вызвал лифт");
        setVisitorInLift(v);
        setLiftFree(false);
        return true;
    }
    else {
        return false;
    }
}

public void moveLift (Visitor v) throws InterruptedException {
        /*
        Задержка в методе moveLift зависит от расстояния между этажами liftFloor и targetFloor, а поле liftFloor в результате вызова moveLift изменяется
         */
        int targetFloor=Math.abs(v.getFloor() -this.liftFloor  );
        Thread.sleep(targetFloor);
        System.out.println("Лифт приехал на " + v.getFloor() + "этаж");

}

public void enterLift (Visitor v) {
    //setLiftFree(false);
    setVisitorInLift(v);
        System.out.println(v.toString() + " вошел в лифт");
}

public void exitFromLift(Visitor v) {
    System.out.println("Выход из лифта");
    liftStatus();
    setLiftFree(true);
    setVisitorInLift(v);
    System.out.println(v.toString() + " вышел из лифта");

     synchronized (blockLift) {
         blockLift.notifyAll();
     }
}

public void liftStatus(){
        if (getLiftFree()) {
            System.out.println("Лифт свободен. На этаже " + getLiftFloor());
        }
    else {
            System.out.println("Лифт занят. На этаже " + getLiftFloor());
        }
}


}