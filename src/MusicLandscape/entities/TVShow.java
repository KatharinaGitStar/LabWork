package MusicLandscape.entities;

import java.util.Objects;

public class TVShow extends Event {
    private String name;
    private int viewers;

    public TVShow(){
        this.name = null;
        this.viewers = 0;
    }
    //constructor that performs a deep copy of an Event
    public TVShow(Event e){
        super(new Event(e)); //call copy constructor of event
        this.viewers = 0;
    }
    public TVShow(TVShow tvs){
        super(tvs);
        this.name = tvs.name;
        this.viewers = tvs.viewers;
    }

    public int getViewers(){
        return this.viewers;
    }

    public void setViewers(int v){
        if (v > 0) {
            this.viewers = v;
        } else {
            this.viewers = 0;
        }
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        String strArtist = getArtist() != null ? getArtist().getName() : "unknown";
        String strDate = getDate() != null ? getDate().toString() : "null";
        String strDescription = getDescription() != null && !getDescription().isEmpty() ? getDescription() : "";
        int attendees = getAttendees() + getViewers();
        int intImpact = impact();
        return String.format("%s @ %s on %s\n%s\n(%d attending (%d))", strArtist, (name != null ? name : "unknown"), strDate, strDescription, attendees, intImpact);
    }

    @Override
    public int impact(){
        return ((viewers + getAttendees()) * 2);
    }

}
