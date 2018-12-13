import java.sql.SQLOutput;
import java.util.Random;

public class Visitor implements Runnable  {
    private BusinessCenter place;
    private static int totalCount; //счётчик посетителей, статическое поле, используемое в конструкторе для при присвоении номера
    private int num; // номер посетителя, инициализируется в конструкторе с использованием totalCount, используется в toString()
    private int floor; // на какой этаж идёт посетитель  (случайное число 1...10)
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public Visitor (BusinessCenter p) throws InterruptedException {
        totalCount++;
        this.num = totalCount;
        Random r1 = new Random();
        this.floor = r1.nextInt(8) + 2;
        this.place = p;
        System.out.println("Пришел" + this.toString() + "; хочет попасть на этаж " + getFloor());
    }

    public void goUp() throws InterruptedException {
        /*
        Методы goUp и goDown после callLiftAndWait вызывают moveLift (для пустого лифта), затем enterLift и второй раз moveLift (для поездки на нужный этаж), и в конце exitLift
        */
        //System.out.println(this + " вызвал лифт");


        if (place.callLiftAndWait(this)) {
            System.out.println(this + " Лифт приехал на 1 этаж");
            this.place.enterLift(this);
            System.out.println(this + " едет c 1 этажа на " + this.floor + " этаж");
            this.place.moveLift(this);
            this.setFloor(this.floor);
            this.place.exitFromLift(this);
            //System.out.println(this + " вышел из лифта");
            //Thread.sleep(100);
        }
    }

    private void doSomeWork() throws InterruptedException{
        System.out.println(this + " что - то делает");
        Thread.sleep(100);
    }

    private void goDown() throws InterruptedException{
        //System.out.println(this + " вызвал лифт");
       // place.callLiftAndWait(this);

                        if (place.callLiftAndWait(this)) {
                            this.place.enterLift(this);
                            System.out.println(this + " едет c " + this.floor + " этажа на 1 этаж");
                            this.setFloor(1);
                            this.place.moveLift(this);
                            this.place.exitFromLift(this);
                            //Thread.sleep(100);
                        }
    }
    public String toString() {
        return " посетитель с №" + num  ;
    }
    @Override
    public void run() {
        try {
            enterBuilding();
           // place.passControl(this);
            this.goUp();
            this.doSomeWork();
            this.goDown();
            //System.out.println(this + " вышел");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void enterBuilding() throws InterruptedException {
        if (place.enterControl(this)) {
            place.passControl(this);
        };
    }
}
