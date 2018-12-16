public class BusinessCenter {
    int liftFloor; // на каком этаже
    Visitor visitorAtControl; //какой посетитель занял проходную если null то проходная пуста
    Visitor visitorInLift; // какой посетитель занял лифт если null то лифт свободен
    boolean liftFree; // флажок лифт занят или нет
    boolean controlFree; //флажок проходная занята или нет
    private static long timeBegin = System.currentTimeMillis();
    String blockLift = "block";
    String blockProhod = "block";

    public BusinessCenter() {
        setLiftFloor(1);
        setLiftFree(true);
        setVisitorAtControl(null);
        setVisitorInLift(null);
        controlFree = true;
    }

    long getTime() {
        return System.currentTimeMillis() - timeBegin;
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

    public void setControlFree(boolean controlFree) {
        this.controlFree = controlFree;
    }

    public boolean getControlFree() {
        return controlFree;
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


        synchronized (blockProhod) {
            while (!getControlFree()) {
                System.out.println(getTime() + " Сообщение " + v + " : Проходная занята " + getVisitorAtControl());
                blockProhod.wait();
            }
            if (getControlFree()) {
                System.out.println(getTime() + " " + v + " занял проходную. Поток " + Thread.currentThread().getId());
                setVisitorAtControl(v);
                setControlFree(false);
                return true;
            } else {
                return false;
            }
        }
    }


    public void passControl(Visitor v) throws InterruptedException {
        //System.out.println(getTime() + " " +"Дошли до контроля");
        /*
        •	Контрольная проверка, что проходная свободна (эта проверка всегда должна проходить при отсутствии ошибок в программе, так что можно в противном случае выбросить исключение)
        •	Thread.sleep для имитации задержки (в реальном приложении там может быть проверка паспорта клиента по базе данных, например)
        •	Код освобождения проходной для последующих посетителей
        •	Отладочная печать на всех этапах
         */
        if (getVisitorAtControl() == v) {
            System.out.println(getTime() + " " + v + " показывает документы. Поток " + Thread.currentThread().getId());
            Thread.sleep(100);
            System.out.println(getTime() + " " + v + " прошел проходную. Поток " + Thread.currentThread().getId());
            exitFromControl(v);
        } else {
            System.out.println(getTime() + " " + "Ахтунг, на проходной посторонний " + getVisitorAtControl());
        }
    }

    public void exitFromControl(Visitor v) {
        synchronized (blockProhod) {
            blockProhod.notify();
        }
        if (getVisitorAtControl() == v) {
            setVisitorAtControl(null);
            controlFree = true;
            System.out.println(getTime() + " Проходная освободилась от " + v);
        }
    }


    public boolean callLift(Visitor v) throws InterruptedException {
        synchronized (blockLift) {
            if (!getLiftFree()) {
                System.out.println(getTime() + " Лифт пока занят. Ждем его с этажа " + getLiftFloor() + " внутри сидит " + getVisitorInLift());
                blockLift.wait();
            }
        }

        if (getLiftFree()) {
            System.out.println(getTime() + " " + v + " вызвал лифт с этажа " + v.vFloor + ", лифт пока на этаже " + getLiftFloor());
            setLiftFree(false);
            Thread.sleep(1);
            return true;
        } else {
            return false;
        }
    }

    public void moveEmptyLift(Visitor v) throws InterruptedException {

        if (getLiftFloor() != v.vFloor) {

            System.out.println(getTime() + " Лифт сейчас пустой на этаже " + getLiftFloor() +", "+ getLiftFree() +", отправляем его на этаж " + v.vFloor);
            int aaa = Math.abs(getLiftFloor()-v.vFloor);
            Thread.sleep(aaa);
            setLiftFloor(v.vFloor);
        }
    }

    void liftDown(Visitor v) throws InterruptedException {
        int i = getLiftFloor(); //5
        // 1>=1+1
        while (getLiftFloor() > v.getFloor()) {
            i--;
            Thread.sleep(10);
            setLiftFloor(i);
            v.vFloor = i;
            System.out.println(getTime() + " " + "Лифт едет с " + getVisitorInLift() + " на этаже" + this.liftFloor);
        }
    }

    void liftUp(Visitor v) throws InterruptedException {
        int i = 1;
        while (getLiftFloor() < v.getFloor()) {
            i++;
            Thread.sleep(10);
            setLiftFloor(i);
            v.vFloor = i;
            System.out.println(getTime() + " " + "Лифт едет с " + getVisitorInLift() + " на этаже" + this.liftFloor);
        }
    }

    public void moveLift(Visitor v) throws InterruptedException {
        if (getLiftFloor() == v.vFloor) {
            //    System.out.println("значит спускаемся c " +  getLiftFloor() + " до " + v.getFloor() );
            liftDown(v);
        }
        if (getLiftFloor() == 1) {
            //   System.out.println("значит поднимаемся c " +  getLiftFloor() + " до " + v.getFloor() );
            liftUp(v);
        }
        // System.out.println(getTime() + " " +"Лифт приехал на " + v.getFloor() + "этаж");
        /*
        Задержка в методе moveLift зависит от расстояния между этажами liftFloor и targetFloor, а поле liftFloor в результате вызова moveLift изменяется
         */
    }

    public void enterLift(Visitor v) throws InterruptedException {
        if (v.vFloor != getLiftFloor()) {
            moveEmptyLift(v);
        }
        //else {
            System.out.println(getTime() + " " + v + "(который на " + v.vFloor + " этаже)" + "вошел в лифт на этаже " + getLiftFloor());
            setVisitorInLift(v);
        //}
    }


    public void exitFromLift(Visitor v) {
        //System.out.println("Пора выходить из лифта");
        setLiftFree(true);
        setVisitorInLift(null);
        System.out.println(getTime() + " " + v + " вышел из лифта на этаже " + getLiftFloor());
        setLiftFloor(v.getFloor());
        synchronized (blockLift) {
            if (getLiftFree()) {
                // System.out.println("убираем блокировку");
                blockLift.notify();
            }
        }
    }



}