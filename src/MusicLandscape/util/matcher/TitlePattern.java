package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class TitlePattern extends MyMatcher<Track>{
    private String pattern;

    public TitlePattern(String pat) {
        super(pat);
        setPattern(pat);
    }

    @Override
    public boolean matches(Track t) {
        return t.getTitle().toLowerCase().contains(pattern.toLowerCase());
    }

    @Override
    public final void setPattern(String pat){
        if(pat != null) {
            pattern = pat;
        }
    }

    @Override
    public String toString(){
        return "title contains (" + pattern + ")";
    }

    @Override
    public String getPattern(){
        return pattern;
    }

}