package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class WriterPattern extends MyMatcher<Track> {
    private String pattern;

    public WriterPattern(String pat) {
        super(pat);
        setPattern(pat);
    }

    @Override
    public boolean matches(Track t) {
        return t.getWriter().getName().toLowerCase().contains(pattern.toLowerCase());
//        return t.getTitle().toLowerCase().contains(pattern.toLowerCase());
    }

    @Override
    public final void setPattern(String pat){
        if(pat != null) {
            pattern = pat;
        }
    }

    @Override
    public String toString(){
        return "performer contains (" + pattern + ")";
    }

    @Override
    public String getPattern(){
        return pattern;
    }

}
