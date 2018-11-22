public class Main {
    public static void main(String[] args) {
        Visitor v = new Visitor(new BusinessCenter());
        v.setFloor(5);
        System.out.println(v.toString());
    }
}
