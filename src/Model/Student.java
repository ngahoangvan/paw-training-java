package Model;

public class Student {
    private int id;
    private String name;
    private int age;
    private int id_class;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Name: " + name + " Age: " + age + " Id Class: " + id_class;
    }
}
