package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class PerformerPattern extends MyMatcher<Track>{
    private String pattern;

    public PerformerPattern(String pat) {
        super(pat);
        setPattern(pat);
    }

    @Override
    public boolean matches(Track t) {
        return t.getPerformer().getName().toLowerCase().contains(pattern.toLowerCase());
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
