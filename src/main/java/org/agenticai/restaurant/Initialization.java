package org.agenticai.restaurant;

import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import org.agenticai.restaurant.booking.Booking;
import org.agenticai.restaurant.booking.NameGenerator;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Initialization {

    @ConfigProperty(name = "restaurant.capacity")
    int capacity;


    @Transactional
    public void init(@Observes StartupEvent ignored) {
        Log.info("Inserting fake data in the database");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            // Pick a date:
            var instant = Instant.now().plus(random.nextInt(0, 7), ChronoUnit.DAYS);
            ZoneId zoneId = ZoneId.systemDefault();
            var date = instant.atZone(zoneId).toLocalDate();

            var party = random.nextInt(2, 10);

            // Make sure we have room for the party
            int sum = Booking.find("date", date).list().stream().map(b -> (Booking) b)
                    .mapToInt(b -> b.partySize)
                    .sum();
            if (sum + party > capacity) {
                party = capacity - sum;
            }

            if (party <= 0) {
                continue;
            }
            createBookingFor(date, NameGenerator.generate(), party);
        }

    }

    void createBookingFor(LocalDate day, String name, int partySize) {
        Booking booking = new Booking();
        booking.date = day;
        booking.name = name;
        booking.partySize = partySize;
        booking.preference = Booking.Preference.UNSET;
        booking.persist();
    }


}
