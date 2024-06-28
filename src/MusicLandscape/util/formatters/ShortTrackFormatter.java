package MusicLandscape.util.formatters;
import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;

public class ShortTrackFormatter extends Object implements MyFormatter<Track> {

    public ShortTrackFormatter() {}

    @Override
    public String header(){

        return "Title      (min:sec)";
    }
    @Override
    public String format(Track t) {
        String title = t.getTitle();
        if (title.length() > 10) {
            title = title.substring(0, 10); // Take first 10 characters
        } else {
            title = String.format("%-10s", title); // Pad with spaces if less than 10 characters
        }
        int minutes = t.getDuration() / 60;
        int seconds = t.getDuration() % 60;
        String duration = String.format("%02d:%02d", minutes, seconds);
        return String.format("%s (%s)", title, duration);
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
