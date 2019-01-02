package kryo;

import java.util.Date;

public class Person {
  private String name = "John Doe";
  private int age = 18;
  private Date birthDate = new Date(933191282821L);

  // standard constructors, getters, and setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}