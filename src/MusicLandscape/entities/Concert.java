package MusicLandscape.entities;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Concert extends Event {
    private int nextIdx;
    private Track[] setList;

    // default constructor
    public Concert() {
        this.setList = new Track[10];
        this.nextIdx = 0;
    }

    public boolean addTrack(Track t) {
        if (t == null) {
            return false;
        }
        ensureCapacity(nextIdx + 1);
        setList[nextIdx++] = t;
        return true;
    }

    private void ensureCapacity(int length){
        if(length > setList.length){
            Track[] newSetList = new Track[setList.length * 2];
            System.arraycopy(setList, 0, newSetList, 0, setList.length);
            setList = newSetList;
        }
    }

    public Track[] getSetList(){
        int cnt = 0;
        for(int i = 0; i < nextIdx; i++){
            if(setList[i] != null){
                cnt++;
            }
        }
        Track[] copy = new Track[cnt];
        int copycnt = 0;
        for (int i = 0; i < nextIdx; i++){
            if(setList[i] != null){
                copy[copycnt++] = new Track(setList[i]); //deep copy of each track
            }
        }
        return copy;
    }

    public void setSetList(Track[] tracks){
        if(tracks == null){
            return; //ignore the null argument
        }

        //create a new set list to store deep copies of non-null tracks
        List<Track> newSetlist = new ArrayList<Track>();
        for(Track track : tracks){
            if(track != null){
                newSetlist.add(new Track(track));
            }
        }
        this.setList = newSetlist.toArray(new Track[0]);
    }

    public void resetSetList(){
        Arrays.fill(setList, null);
        nextIdx = 0;
    }

    public int nrTracks(){
        return nextIdx;
    }

    public int duration(){
        int total_duration = 0;
        if(setList != null){
            for(Track track : setList){
                if(track != null){
                    total_duration += track.getDuration(); //track duration in seconds, 0 if unknown

                }
            }
        }
        return total_duration;
    }

    @Override
    public int impact(){ // calculate the impact of the concert
        double con_impact = 1;
        if(duration() > (30*60)){ //duration in seconds
            con_impact = Math.ceil((double)duration()/(30*60));
        }
        return (int) (con_impact * getAttendees());
    }

    @Override
    public String toString(){

        String baseString = super.toString();
        // get the number of tracks from the list
        int numTracks = setList != null ? setList.length: 0;
        int totalDuration = duration();
        int hours = totalDuration / 3600;
        int minutes = (totalDuration % 3600) / 60;
        String durationString = String.format("%02d:%02d", hours, minutes);

        return String.format("%s\n%d tracks played, total duration %s.", baseString, numTracks, durationString);
    }
}
