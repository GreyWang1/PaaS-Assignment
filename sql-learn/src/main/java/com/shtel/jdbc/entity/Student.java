package com.shtel.jdbc.entity;

public class Student {
    private Integer sno;
    private String name;
    private Integer age;

    public Integer getId() {
        return sno;
    }

    public void setId(Long id) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + sno +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
