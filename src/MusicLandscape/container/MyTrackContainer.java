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
        this();
        addAll(t);
        reset();
    }

    //sort the selection of tracks (performer, writer, etc.)
    public void sort(Comparator<Track> theComp, boolean asc) {
        Collections.sort(selection, theComp);
        if (!asc) {
            Collections.reverse(selection);
        }
    }

    // Filter the selection based on a matcher
    public int filter(MyMatcher<Track> matcher) {
        List<Track> newSelection = new ArrayList<>();
        for (Track track : selection) {
            if (matcher.matches(track)) {
                newSelection.add(track);
            }
        }
        int removedCount = selection.size() - newSelection.size();
        selection = newSelection;
        return removedCount;
    }


    //add a single track, if not added already and if != null
    public boolean add(Track t) {
        if(t == null || tracks.contains(t)){
            return false;
        }
        boolean added = tracks.add(t);
        selection.add(t);

        return added;
    }

    // Add this method to return all tracks
    public List<Track> getAllTracks() {
        return new ArrayList<>(tracks);
    }


    // Get the size of the container
    public int size() {
        return tracks.size();
    }

    //gets the selected tracks and return as an array of tracks
    //if the selection is empty an array of size 0 is returned
    public Track[] selection() {
        return selection.toArray(new Track[0]);
    }

    //bulk operation to add tracks
    public int addAll(Track[] t) {
        int count = 0;
        for (Track track : t) {
            if (add(track)) {
                count++;
            }
        }
        return count;
    }


    //all selected tracks are removed
    public int remove() {
        int removedCount = 0;
        for (Track track : selection) {
            if (tracks.remove(track)) {
                removedCount++;
            }
        }
        reset();
        return removedCount;
    }


    //Get a track by index
    public Track get(int index) {
        if (index >= 0 && index < selection.size()) {
            return selection.get(index);
        }
        return null;
    }

    //method to reset the selection
    public void reset(){
        selection.clear();
        selection.addAll(tracks);
    }

    // Check if the container is empty
    public boolean isEmpty() {
        return tracks.isEmpty();
    }

    public void resetSelection() {
        selection.clear();
        selection.addAll(tracks);
    }

    public void removeSelection() {
        tracks.removeAll(selection);
        selection.clear();
    }
}
