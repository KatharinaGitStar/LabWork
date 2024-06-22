package MusicLandscape.container;
import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;
import java.util.*;

public class MyTrackContainer extends Object{
    private Set<Track> tracks;
    private List<Track> selection;

    public MyTrackContainer() {
        tracks = new HashSet<>();
        selection = new ArrayList<>();
    }

    public MyTrackContainer(Iterable<Track> t) {
        this();
        for(Track track : t){
            add(track);
        }
        reset();
    }

    public MyTrackContainer(Track[] t) {
        this(Arrays.asList(t));
    }

    public void sort(Comparator<Track> theComp, boolean asc) {
        if (asc) {
            Collections.sort(selection, theComp);
        } else {
            Collections.sort(selection, Collections.reverseOrder(theComp));
        }
    }

    public int filter(MyMatcher<Track> matcher) {
        Iterator<Track> iterator = selection.iterator();
        int removed = 0;
        while (iterator.hasNext()) {
            Track track = iterator.next();
            if (!matcher.matches(track)) {
                iterator.remove();
                removed++;
            }
        }
        return removed;
    }

    //add a single track, if not added already and if != null
    public boolean add(Track t) {
        if(tracks.contains(t) || t == null ){
            return false;
        }
        tracks.add(t);
        return true;
    }

    //return the number of track currently held by this container
    public int size(){
        return tracks.size();
    }

    //gets the selected tracks and return as an array of tracks
    //if the selection is empty an array of size 0 is returned
    public Track[] selection() {
        return selection.toArray(new Track[0]);
    }

    //bulk operation to add tracks
    public int addAll(Track[] t){
        int cnt = 0;
        for(Track track : t){
            if(add(track)){
                cnt++;
            }
        }
        return cnt;
    }

    //all selected tracks are removed
    public int remove(){
        int removed = selection.size();;
        tracks.removeAll(selection);
        reset();
        return removed;
    }



    //method to reset the selection
    public void reset(){
        selection.clear();
        selection.addAll(tracks);
    }
}
