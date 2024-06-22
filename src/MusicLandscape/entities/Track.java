package MusicLandscape.entities;

import MusicLandscape.util.ConsoleScanable;

import java.util.Scanner;

public class Track implements ConsoleScanable {
    private String title;
    private int duration;
    private Artist writer = new MusicLandscape.entities.Artist();
    private Artist performer = new MusicLandscape.entities.Artist();
    private int year;

    //default constructor
    public Track(){
        this.title = null;
        this.duration = 0;
        this.writer = new Artist("Default writer");
        this.performer = new Artist("Default performer");
        this.year = 1900;
    }
    //copy constructor -> deep copy
    public Track(Track t){
        this.title = t.title;
        this.duration = t.duration;
        this.writer = new Artist(t.writer);
        this.performer = new Artist(t.performer);
        this.year = t.year;
    }
    //constructor, with a certain title
    public Track(String title){
        this.title = title;
        this.duration = 0;
        this.writer = new Artist("Default writer");
        this.performer = new Artist("Default performer");
        this.year = 1900;
    }

    public int getYear() {
        return year;
    }

    public boolean setYear(int year){
        if(year > 1899 && year < 3000) {
            this.year = year;
        }
        else {
            return true;
        }
        return false;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if(duration >= 0){
            this.duration = duration;
        }
        else {
            this.duration = 0;
        }

    }

    public String getTitle() {
        if (title == null || title.trim().isEmpty()) {
            return this.title = "unknown title";
        } else {
            return this.title;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getWriter() {
        return writer;
    }

    public void setWriter(Artist writer) {
        if (writer != null) {
            this.writer = writer;
        }
    }

    public Artist getPerformer() {
        return performer;
    }

    public void setPerformer(Artist performer) {
        if (performer != null) {
            this.performer = performer;
        }
    }

    public boolean writerIsKnown() {
        //writer != null checks if the writer object is not null.
        //writer.getName() != null checks if the getName() method returns a non-null value
        return writer != null && writer.getName() != null;
    }

    public String getString() {
        String trackInfo = "";

        // Append title or "unknown" if title is null
        if (title != null && !title.isEmpty()) {
            // take the first 10 characters of the title
            String shortenedTitle = title.substring(0, Math.min(title.length(), 10));

            if ("song".equals(shortenedTitle.toLowerCase())) {
                trackInfo += "      song by";
            } else {
                trackInfo += shortenedTitle + " by ";
            }
        } else {
            trackInfo += "   unknown by ";
        }


        // append writer "unknown" if writer is null
        if(writer != null) {
            if(writer.getName() == null) {
                trackInfo += "    unknown ";
            }
            else{
                // take the first 10 characters of writer
                String shortenWriter = writer.getName().substring(0, Math.min(writer.getName().length(), 10));
                if(shortenWriter.equals("writer")) {
                    trackInfo += "    " + shortenWriter + " ";
                }
                else{
                    trackInfo += shortenWriter + " ";
                }

            }
        }
        else{
            trackInfo += "   unknown ";
        }

        // append writer "unknown" if writer is null
        if (performer != null) {
            if (performer.getName() == null) {
                trackInfo += "performed by    unknown";
            }
            else {
                // take the first 10 characters of performer
                String shortenPerformer = performer.getName().substring(0, Math.min(performer.getName().length(), 10));
                if(shortenPerformer.equals("performer")){
                    trackInfo += "performed by  " + shortenPerformer;
                }
                else{
                    trackInfo += "performed by " + shortenPerformer;
                }
            }
        }
        else {
            trackInfo += "performed by    unknown";
        }



        // Format duration as minutes and seconds
        int minutes = duration / 60;
        int seconds = duration % 60;
        String durationString = String.format(" (%02d:%02d)", minutes, seconds);

        // Append duration
        trackInfo += durationString;

        return trackInfo; // Trim leading/trailing whitespace
    }

    @Override
    public String toString() {
        return getString();
    }


    @Override
    public boolean scan() {
        Scanner scanner = new Scanner(System.in);
        boolean objectChanged = false;

        //Scan title
        while (true) {
            System.out.print("Title (" + title + "): ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                title = input;
                objectChanged = true;
                break;
            } else {
                System.out.println("Title unchanged.");
                break;
            }
        }
        //Scan duration
        while (true) {
            System.out.print("Duration (" + duration + " seconds): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Duration unchanged.");
                break; // Keep the old value
            }
            try {
                int newDuration = Integer.parseInt(input);
                if (newDuration > 0) {
                    duration = newDuration;
                    objectChanged = true;
                    break;
                } else {
                    System.out.println("Duration must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid duration in seconds.");
            }
        }
        System.out.println("Track details updated.");
        return objectChanged;
    }
}

