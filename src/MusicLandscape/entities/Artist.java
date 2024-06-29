package MusicLandscape.entities;

public class Artist extends Object implements Comparable<Artist>{
    private String name;

    // Constructor with a default name unknown
    public Artist() {
        this.name = "unknown";
    }

    public Artist(Artist a) {
        this.name = a.name;
    }

    public Artist(String name) {
        this.name = name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "unknown";
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        if (name == null || name.trim().isEmpty()) {
            return "unknown";
        }
        else {
            return name;
        }
    }


    @Override
    public int compareTo(Artist other) {
        if (other == null || other.getName() == null) {
            return 1;
        }
        if (this.name == null) {
            return -1;
        }
        return this.name.compareToIgnoreCase(other.getName());
    }
}
