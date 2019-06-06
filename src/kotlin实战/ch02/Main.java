package kotlin实战.ch02;

/**
 * @author VincentJ
 * @date 2019-06-06
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Person("Bob",true);
        // 注意没有setName()
        System.out.println(person.getName());
        person.setMarried(false);
        System.out.println(person.isMarried());
    }
}
