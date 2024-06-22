package MusicLandscape.util.comparators;

import MusicLandscape.entities.Event;
import java.util.Date;
import java.util.Calendar;

public class MyEventDateComparator extends MyEventComparator {

    @Override
    public int compare(Event e1, Event e2) {
        //compares 2 events by date; handles null arguments, a null event is always smaller
        //than any non-null event.
        if (e1 == null && e2 == null) {
            return 0; // Both are null, they are equal
        } else if (e1 == null) {
            return -1; // e1 is null and e2 is not null, e1 is smaller
        } else if (e2 == null) {
            return 1; // e2 is null and e1 is not null, e2 is smaller
        }
        // Both e1 and e2 are not null, so compare their dates
        if (e1.getDate() == null && e2.getDate() == null) {
            return 0; // Both dates are null
        } else if (e1.getDate() == null) {
            return -1; // e1's date is null and e2's date is not null, e1's date is considered smaller
        } else if (e2.getDate() == null) {
            return 1; // e2's date is null and e1's date is not null, e2's date is considered smaller
        } else {   // Use compareTo() to compare the dates
            // Use compareTo() to compare the dates
            Date date1 = e1.getDate();
            Date date2 = e2.getDate();
            Date normalizedDate1 = normalizeDate(date1);
            Date normalizedDate2 = normalizeDate(date2);
            return  normalizedDate1.compareTo(normalizedDate2);
        }
    }


    public Date normalizeDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}


