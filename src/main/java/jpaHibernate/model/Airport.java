package jpaHibernate.model;

import jpaHibernate.service.AirportPersistenceService;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Airport extends EntityWithSurrogateKey {

  private String name;
  private String location;

  @OneToMany(mappedBy = "airport", cascade = CascadeType.PERSIST)
  private List<Plane> planes;

  public Airport() {
    this.planes = new ArrayList<>();
  }

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

  public List<Airport> fetchInformation() {
    return new AirportPersistenceService().fetchPlanes();
  }

  /**
   * Call fetchInformation before!
   */
  public List<Plane> getPlanes() {
    return planes;
  }

  public void setPlanes(List<Plane> planes) {
    this.planes = planes;
  }

  public void addPlane(Plane plane) {
    plane.setAirport(this);
    this.planes.add(plane);
  }
}
