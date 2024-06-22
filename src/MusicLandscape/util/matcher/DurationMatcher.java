package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;


public class DurationMatcher  extends MyMatcher <Track> {
    private int lower;
    private int upper;

    public DurationMatcher() {
        super("");
        this.lower = 0;
        this.upper = Integer.MAX_VALUE;
    }

    public DurationMatcher(String pat) {
        super(pat);
        setPattern(pat);
    }

    @Override
    public boolean matches(Track t) {
        if (t == null) {
            return false;
        }
        int duration = t.getDuration();
        return duration >= lower && duration <= upper;
    }

    @Override
    public String getPattern() {
        return lower + " " + upper;
    }

    @Override
    public void setPattern(String pat) {
        if (pat != null) {
            try {
                //trim for whitespaces
                pat = pat.trim();
                //split pattern into parts based on whitespace
                String[] parts = pat.split("\\s+");

                //check the number of parts in the pattern
                if (parts.length == 1) { //if there is only 1 part, the lower boundary is set
                    lower = Integer.parseInt(parts[0]);
                    upper = Integer.MAX_VALUE;
                } else if (parts.length == 2) { //if there are 2 parts, the lower and upper boundary are set
                    lower = Integer.parseInt(parts[0]);
                    upper = Integer.parseInt(parts[1]);
                }

                //check if the lower boundary is greater than the upper, if so set the lower to the first part and the upper to max.
                if (lower > upper) {
                    lower = Integer.parseInt(parts[0]);
                    upper = Integer.MAX_VALUE;
                }
            } catch (NumberFormatException e) {
            }
        }
    }
    @Override
    public String toString () {
        return "duration in range (" + getPattern() + ")";
    }
}
