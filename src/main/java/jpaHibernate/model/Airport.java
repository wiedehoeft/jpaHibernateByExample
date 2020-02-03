package jpaHibernate.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Airport extends EntityWithSurrogateKey {

  private String name;
  private String location;

  @OneToMany(mappedBy = "airport")
  private List<Plane> planes;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<Plane> getPlanes() {
    return planes;
  }

  public void setPlanes(List<Plane> planes) {
    this.planes = planes;
  }
}
