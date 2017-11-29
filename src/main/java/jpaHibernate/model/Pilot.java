package jpaHibernate.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pilot",
        uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))
public class Pilot extends EntityWithSurrogateKey {

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  private int age;

  @OneToOne(mappedBy = "pilot")
  private Plane plane;

  //TODO: Currently not working with H2
  @Temporal(TemporalType.DATE)
  @Column(columnDefinition = " DATE DEFAULT CURRENT_DATE ",
          insertable = false, updatable = false)
  private Date eintritt;

  @Transient
  public String getName() {
    return firstName + " " + lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Date getEintritt() {
    return eintritt;
  }
}
