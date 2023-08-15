package Lesson65;

public class AppTest {
    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory("Lesson65");

        Animal tiger = (Animal) factory.getBean("tiger");
        tiger.setName("tom");
        tiger.setAge(4);
        System.out.println(tiger);
    }
}
