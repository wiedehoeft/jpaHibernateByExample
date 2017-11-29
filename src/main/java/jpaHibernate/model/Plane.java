package jpaHibernate.model;

import javax.persistence.*;

@Entity
public class Plane extends EntityWithSurrogateKey {

  private String name;
  private int capacity;

  @ManyToOne(optional = false, cascade = CascadeType.ALL) //same as nullable false
  @JoinColumn(name = "planesPerAirport", nullable = false)
  private Airport airport;

  @OneToOne(optional = false, cascade = CascadeType.ALL)
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
    this.pilot = pilot;
  }
}
