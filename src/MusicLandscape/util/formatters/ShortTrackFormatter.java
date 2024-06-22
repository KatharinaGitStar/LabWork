package MusicLandscape.util;
import MusicLandscape.entities.Track;

public class ShortTrackFormatter extends Object implements MyFormatter<Track> {

    public ShortTrackFormatter() {}

    @Override
    public String header(){
        return "Title      (min:sec)";
    }
    @Override
    public String format(Track t) {
        String title = t.getTitle().substring(0, Math.min(t.getTitle().length(), 10)); // Ensure title is exactly ten characters wide
        title = String.format("%-10s", title);
        String duration = formatDuration(t.getDuration()); // Format duration as "min:sec"
        return title + " (" + duration + ")";
    }

    @Override
    public String toString() {
        return "short format [Title (min:sec)]";
    }

    //top separator consists of dashes (-) only. It is exactly as wide as the header.
    @Override
    public String topSeparator(){
        return "--------------------";
    }

    // Method to format duration as "min:sec"
    private String formatDuration(int duration) {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
