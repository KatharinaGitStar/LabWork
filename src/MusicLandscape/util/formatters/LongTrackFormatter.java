package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;

/**
 * A formatter to create a detailed string representation of a track.
 *
 * @version 232
 * @Author TeM
 * @Stage LW
 */
public class LongTrackFormatter implements MyFormatter<Track> {

    /**
     * Default constructor.
     */
    public LongTrackFormatter() {}

    /**
     * Get the headers for the table as a single string.
     * Contains the names for all columns separated by the correct number of blanks.
     *
     * @return the header string.
     */
    @Override
    public String header() {
        return String.format("%-20s %-20s %-20s %-10s %-4s", "Title", "Writer", "Performer", "Duration", "Year");
    }

    /**
     * A line of text to be used between header and data.
     *
     * @return the separator.
     */
    @Override
    public String topSeparator() {
        return "--------------------------------------------------------------------------------";
    }

    /**
     * Creates a String representation for a track.
     *
     * @param t the track to be formatted
     * @return the formatted string representing the track
     */
    @Override
    public String format(Track t) {
        if (t == null) {
            return "";
        }
        return String.format("%-20s %-20s %-20s %-10s %-4d",
                edit_string(t.getTitle(), 20),
                edit_string(t.getWriter().getName(), 20),
                edit_string(t.getPerformer().getName(), 20),
                formatDuration(t.getDuration()),
                t.getYear());
    }

    private String edit_string(String str, int length) {
        if(str == null){
            str = "";
        }
        //trim leading spaces
        str = str.replaceAll("\\s", "");
        if(str.length() <= length){
            return String.format("%-" + length + "s", str);
        } else {
            return str.substring(0, length);
        }

    }

    /**
     * Formats the duration from seconds to a min:sec format.
     *
     * @param duration the duration in seconds
     * @return the formatted duration
     */
    private String formatDuration(int duration) {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Returns a string representation of the formatter.
     *
     * @return a string representation of the formatter
     */
    @Override
    public String toString() {
        return "LongTrackFormatter";
    }
}
