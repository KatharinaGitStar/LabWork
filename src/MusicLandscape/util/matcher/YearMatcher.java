package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;


public class YearMatcher  extends MyMatcher<Track> {
    private int lower;
    private int upper;

    public YearMatcher() {
        super("");
        this.lower = 0;
        this.upper = Integer.MAX_VALUE;
    }

    public YearMatcher(String pat) {
        super(pat);
        setPattern(pat);
    }

    @Override
    public boolean matches(Track t) {
        if (t == null) {
            return false;
        }
        int year = t.getYear();
        return year >= lower && year <= upper;
    }

    @Override
    public String getPattern() {
        return lower + " " + upper;
    }

    @Override
    public void setPattern(String pat) {
        String[] parts = pat.split(" ");
        if (parts.length == 2) {
            try {
                lower = Integer.parseInt(parts[0]);
                upper = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                lower = 0;
                upper = Integer.MAX_VALUE;
            }
        } else {
            lower = 0;
            upper = Integer.MAX_VALUE;
        }
        //System.out.println("Pattern set: lower=" + lower + ", upper=" + upper);
    }

    @Override
    public String toString () {
        return "year in range (" + getPattern() + ")";
    }
}
