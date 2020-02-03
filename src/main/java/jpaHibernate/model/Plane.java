package jpaHibernate.model;

import javax.persistence.*;

@Entity
public class Plane extends EntityWithSurrogateKey {

  private String name;
  private int capacity;

  @ManyToOne(optional = false) //same as nullable false
  @JoinColumn(nullable = false)
  private Airport airport;

  @OneToOne(optional = false, cascade = CascadeType.PERSIST)
  private Pilot pilot;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Airport getAirport() {
    return airport;
  }

  public void setAirport(Airport airport) {
    this.airport = airport;
  }

  public Pilot getPilot() {
    return pilot;
  }

  public void setPilot(Pilot pilot) {
    pilot.setPlane(this);
    this.pilot = pilot;
  }
}
