public class Main {

    public static void main(String[] args) {
        Car one = new Car("Первый", 200);
        System.out.println(one.name);
    }
}

class Car {
    String name;
    int speed;
    public Car(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }
}
