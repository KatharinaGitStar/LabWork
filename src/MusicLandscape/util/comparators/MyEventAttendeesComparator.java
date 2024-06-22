package MusicLandscape.util.comparators;

import MusicLandscape.entities.Event;

public class MyEventAttendeesComparator extends MyEventComparator{
    @Override
    public int compare(Event e1, Event e2) {
        //compares two events by # of attendees; null-argument: 2 null are equal; any null is always smaller
        if(e1 == null && e2 == null){
            return 0;
        }
        else if(e1 == null){
            return -1;
        }
        else if(e2 == null){
            return 1;
        }
        Integer a1 = (Integer)e1.getAttendees();
        Integer a2 = (Integer)e2.getAttendees();

        return a1.compareTo(a2);
    }
}
