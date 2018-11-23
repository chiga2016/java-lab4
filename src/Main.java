public class Main {
    public static void main(String[] args) throws InterruptedException  {
        /*
        •	cоздавать 1 бизнес-центр и n посетителей с некоторой задержкой,
        •	запускать для каждого из посетителей отдельный поток
        •	ждать их завершения

         */

        long timeNow = System.currentTimeMillis();

        BusinessCenter bc1 = new BusinessCenter();
/*
        for (int i=1; i <6; i++) {

            (new Thread(new Visitor(bc1))).start();
            Thread.sleep(1000);

        }
*/
/*
        Visitor v2 = new Visitor(bc1);
        Visitor v3  =new Visitor(bc1);
        Visitor v4 = new Visitor(bc1);
        Visitor v5 = new Visitor(bc1);

        Thread t1 = new Thread();
        Thread t2 = new Thread();
        Thread t3 = new Thread();
        Thread t4 = new Thread();
        Thread t5 = new Thread();

        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        t3.start();
        Thread.sleep(1000);
        t4.start();
        Thread.sleep(1000);
        t5.start();
*/

        long timeNow2 = System.currentTimeMillis();
        System.out.println(timeNow2-timeNow);











//        System.out.println("Первый этаж" + (timeNow -System.currentTimeMillis()));
//        Thread.sleep(1000);
//
//        System.out.println("Второй этаж" + (timeNow -System.currentTimeMillis()));
//        Thread.sleep(1000);
//        System.out.println("Третий этаж" + (timeNow -System.currentTimeMillis()));
//        Thread.sleep(1000);
//        System.out.println("Четвертый этаж" + (timeNow -System.currentTimeMillis()));
//        Thread.sleep(1000);
//        System.out.println("Пятый этаж" + (timeNow -System.currentTimeMillis()));
//        Thread.sleep(1000);
//        System.out.println("Шестой этаж" + (timeNow -System.currentTimeMillis()));
//        Thread.sleep(1000);

        //long timeNow2 = System.currentTimeMillis();
        //System.out.println(timeNow2-timeNow);
//        Thread t =new Thread();
//        t.s


    }
}
