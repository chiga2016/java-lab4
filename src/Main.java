public class Main {
    public static void main(String[] args) throws InterruptedException  {
        /*
        •	cоздавать 1 бизнес-центр и n посетителей с некоторой задержкой,
        •	запускать для каждого из посетителей отдельный поток
        •	ждать их завершения
         */
        long timeNow = System.currentTimeMillis();
        BusinessCenter bc1 = new BusinessCenter();
        for (int i=1; i <4; i++) {
            (new Thread(new Visitor(bc1))).start();
            //Thread.sleep(1000);
        }


        long timeNow2 = System.currentTimeMillis();
        //System.out.println(timeNow2-timeNow);
    }
}
