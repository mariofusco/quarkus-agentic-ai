package org.agenticai.restaurant.booking;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Booking extends PanacheEntity {
    public LocalDate date;
    public String name;
    public int partySize;
    public Preference preference;



    public enum Preference {
        INDOOR, OUTDOOR, UNSET
    }
}
