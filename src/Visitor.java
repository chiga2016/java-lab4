import java.sql.SQLOutput;
import java.util.Random;

public class Visitor implements Runnable  {
    private BusinessCenter place;
    private static int totalCount; //счётчик посетителей, статическое поле, используемое в конструкторе для при присвоении номера
    private int num; // номер посетителя, инициализируется в конструкторе с использованием totalCount, используется в toString()
    private int floor; // на какой этаж идёт посетитель  (случайное число 1...10)

    public Visitor (BusinessCenter p) throws InterruptedException {
        totalCount++;
        this.num = totalCount;
        Random r1 = new Random();
        this.floor = r1.nextInt(8) + 2;
        this.place = p;

        System.out.println("Пришел" + this.toString());
        place.enterControl(this);
    }
    public void goUp() {
        /*
        Методы goUp и goDown после callLiftAndWait вызывают moveLift (для пустого лифта), затем enterLift и второй раз moveLift (для поездки на нужный этаж), и в конце exitLift
        */
    }



    public void goDown() {
    }
    public void doSomeWork() {
    }
    public String toString() {
        return " посетитель с №" + num + "; хочет попасть на этаж " + getFloor() ;
    }

    @Override
    public void run() {



    }




    public void enterBuilding() throws InterruptedException {
        if (place.enterControl(this)) {
            place.passControl(this);
        };



    }



    public int getFloor() {
        return floor;
    }

    public int getNum() {
        return num;
    }
    /*

    public private getBusinessCenter() {
        return BusinessCenter;
    }

    public void setBusinessCenter(private businessCenter) {
        BusinessCenter = businessCenter;
    }
*/
    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
