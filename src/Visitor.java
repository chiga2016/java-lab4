public class Visitor implements Runnable  {
    private BusinessCenter Place;
    private  int totalCount;
    private int num;
    private int floor;


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
        return null;
    }

    @Override
    public void run() {

    }




    public void enterBuilding(){

    }

    public Visitor (BusinessCenter p){
        totalCount++;
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
