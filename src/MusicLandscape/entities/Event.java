package MusicLandscape.entities;

import MusicLandscape.Venue;

import java.util.Date;

public class Event {
    private Artist artist;
    private int attendees;
    private Date date;
    private String description;
    private Venue venue;

    public Event(){
        this.artist = new Artist();
        this.description = "";
    }

    public Event (Event e){
        this.artist = new Artist(e.getArtist());
        this.attendees = e.getAttendees();
        this.date = new Date(e.getDate().getTime());
        this.description = e.getDescription();
        this.venue = new Venue(e.getVenue());
    }

    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        if(artist != null) {
            this.artist = artist;
        }
    }
    public Venue getVenue(){
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public Date getDate() {
        if(date == null){
            return null;
        }else {
            return new Date(date.getTime());
        }
    }
    public void setDate(Date date) {
        if(date != null) {
            this.date = new Date(date.getTime());
        }
        else{
            this.date = null;
        }
    }
    public int getAttendees() {
        return attendees;
    }
    public void setAttendees(int attendees) {
        if(attendees >= 0) {
            this.attendees = attendees;
        }
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if(description != null){
            this.description = description;
        }
        else{
            this.description = "";
        }
    }

    @Override
    public String toString(){
        String artistStr = (artist != null && artist.toString() != null && !artist.toString().isEmpty()) ? artist.toString() : "unknown";
        String venueStr = (venue != null && venue.getName() != null && !venue.getName().isEmpty()) ? venue.getName() : "unknown";
        String dateStr = (date != null) ? date.toString() : null;  // Assuming Date class has a proper toString method
        String descriptionStr = (description != null && !description.isEmpty()) ? description : "";
        String attendeesStr = (attendees > 0) ? String.valueOf(attendees) : (attendees == 0) ? String.valueOf(0) : "unknown";

        // Assuming impact is calculated in some way, otherwise set it to "unknown"
        String impactStr = String.valueOf(impact());

        return String.format("%s @ %s on %s%n%s%n(%s attending (%s))",
                artistStr, venueStr, dateStr, descriptionStr, attendeesStr, impactStr);

    }

    public int impact(){
        return (attendees * 2);
    }

}
