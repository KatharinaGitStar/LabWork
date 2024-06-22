package MusicLandscape.entities;

public class Album extends Release {
    private TrackListItem trackListHead;

    public Album() {
        super();
        this.trackListHead = null;
    }

    // Copy constructor
    public Album(Album orig) {
        super(orig);
        if (orig.trackListHead != null) {
            this.trackListHead = new TrackListItem(orig.trackListHead.track);
            TrackListItem currentOrig = orig.trackListHead.next;
            TrackListItem currentCopy = this.trackListHead;
            while (currentOrig != null) {
                currentCopy.next = new TrackListItem(currentOrig.track);
                currentCopy = currentCopy.next;
                currentOrig = currentOrig.next;
            }
        }
    }

    // Custom constructor
    public Album(String title, Artist artist, int year) {
        super(title, artist, year);
        this.trackListHead = null;
    }

    // Nested class TrackListItem
    private class TrackListItem {
        private Track track;
        private TrackListItem next;

        public TrackListItem(Track track) {
            this.track = track;
            this.next = null;
        }
    }

    // Method to add a track to the album
    public boolean addTrack(Track t) {
        if (t == null) return false;
        TrackListItem newItem = new TrackListItem(t);
        if (trackListHead == null) {
            trackListHead = newItem;
        } else {
            TrackListItem current = trackListHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newItem;
        }
        return true;
    }

    // Method to remove a track by index
    public Track removeTrack(int n) {
        if (trackListHead == null || n < 0) return null;
        if (n == 0) {
            Track removed = trackListHead.track;
            trackListHead = trackListHead.next;
            return removed;
        }
        TrackListItem current = trackListHead;
        for (int i = 0; current != null && i < n - 1; i++) {
            current = current.next;
        }
        if (current == null || current.next == null) return null;
        Track removed = current.next.track;
        current.next = current.next.next;
        return removed;
    }

    // Method to get the number of tracks
    public int nrTracks() {
        int count = 0;
        TrackListItem current = trackListHead;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Method to get an array of tracks
    public Track[] getTracks() {
        int size = nrTracks();
        Track[] tracks = new Track[size];
        TrackListItem current = trackListHead;
        int index = 0;
        while (current != null) {
            tracks[index++] = current.track;
            current = current.next;
        }
        return tracks;
    }

    // Method to get the total running time of the album in seconds
    public int totalTime() {
        int total = 0;
        TrackListItem current = trackListHead;
        while (current != null) {
            total += current.track.getDuration();
            current = current.next;
        }
        return total;
    }


    @Override
    public String toString() {
        //string representation of this album; adds titles of all tracks.
        //The list of track names is enclosed by opening and closing brackets [,]
        //Track titles are enclosed by opening and closing brackets.

        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n[");
        TrackListItem current = trackListHead;
        while (current != null) {
            // Extract the track title from the full track string representation
            String trackTitle = current.track.getTitle();
            sb.append("[").append(trackTitle).append("]");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
