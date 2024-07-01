package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class WriterMatcher extends MyMatcher<Track> {
    private String pattern;

    public WriterMatcher(String pat) {
        super(pat);
        setPattern(pat);
    }

    @Override
    public boolean matches(Track t) {
        if (t == null || t.getWriter() == null || t.getWriter().getName() == null) {
            return false;
        }

        String writerName = t.getWriter().getName().toLowerCase().trim();
        boolean result = writerName.startsWith(pattern.toLowerCase().trim());

        //System.out.println("Checking writer: " + writerName + " against pattern: " + pattern + " -> " + result);

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
        return "writer starts with (" + pattern + ")";
    }

    @Override
    public String getPattern(){
        return pattern;
    }

}