package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class TitleMatcher extends MyMatcher<Track>{
    private String pattern;

    public TitleMatcher(String pat) {
        super(pat);
        setPattern(pat);
    }

    @Override
    public boolean matches(Track t) {
        return t.getTitle().startsWith(pattern);
    }

    @Override
    public final void setPattern(String pat){
        if(pat != null) {
            pattern = pat;
        }
    }

    @Override
    public String toString(){
        return "title starts with (" + pattern + ")";
    }

    @Override
    public String getPattern(){
        return pattern;
    }

}