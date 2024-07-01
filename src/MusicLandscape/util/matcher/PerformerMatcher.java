package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class PerformerMatcher extends MyMatcher<Track> {
    private String pattern;

    public PerformerMatcher(String pat) {
        super(pat);
        setPattern(pat);
    }

    @Override
    public boolean matches(Track t) {

        if (t == null || t.getWriter() == null || t.getWriter().getName() == null) {
            return false;
        }

        String performerName = t.getPerformer().getName().toLowerCase().trim();
        boolean result = performerName.startsWith(pattern.toLowerCase().trim());

        return result;
    }

    @Override
    public final void setPattern(String pat){
        if(pat != null) {
            pattern = pat;
        }
    }

    @Override
    public String toString(){
        return "performer starts with (" + pattern + ")";
    }

    @Override
    public String getPattern(){
        return pattern;
    }

}
