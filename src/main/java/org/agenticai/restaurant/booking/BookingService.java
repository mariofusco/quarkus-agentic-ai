package org.agenticai.restaurant.booking;

import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BookingService {

    private final int capacity;

    public BookingService(@ConfigProperty(name = "restaurant.capacity") int capacity) {
        this.capacity = capacity;
    }

    public boolean hasCapacity(LocalDate date, int partySize) {
        int sum = Booking.find("date", date).list().stream().map(b -> (Booking) b)
                .mapToInt(b -> b.partySize)
                .sum();
        return sum + partySize <= capacity;
    }

    @Transactional
    @Tool("Books a table for a given name, date (passed as day fo the month, month and year), party size and preference (indoor/outdoor). If the restaurant is full, an exception is thrown. If preference is not specified, `UNSET` is used.")
    public String book(String name, int day, int month, int year, int partySize, Booking.Preference preference) {
        var date = LocalDate.of(year, month, day);
        if (hasCapacity(date, partySize)) {
            Booking booking = new Booking();
            booking.date = date;
            booking.name = name;
            booking.partySize = partySize;
            if (preference == null) {
                preference = Booking.Preference.UNSET;
            }
            booking.preference = preference;
            booking.persist();
            String result = String.format("%s successfully booked a %s table for %d persons on %s", name, preference, partySize, date);
            Log.info(result);
            return result;
        }
        return "The restaurant is full for that day";
    }


}
