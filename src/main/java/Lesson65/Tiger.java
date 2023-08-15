package Lesson65;

import Lesson65.annotations.Component;

@Component
public class Tiger implements Animal {
    private String name;
    private int Id;

    public Tiger(String name, int id) {
        this.name = name;
        Id = id;
    }

    public Tiger() {
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "name='" + name + '\'' +
                ", Id=" + Id +
                '}';
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.Id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAge(int id) {
        this.Id = id;
    }
}
