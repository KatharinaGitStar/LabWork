package MusicLandscape.util.io;
import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Track;

import java.io.BufferedReader;
import java.io.IOException;

public class MyTrackCSVReader extends MyReader<Track>{

    private static int TITLE = 0;
    private static int WRITER = 1;
    private static int PERFORMER = 2;
    private static int DURATION = 3;
    private static int YEAR = 4;

    public MyTrackCSVReader(BufferedReader in){
        super(in);
    }

    @Override
    public Track get(){
        try{
            String line = in.readLine();
            if(line != null) {
                System.out.println(line);
                String[] tokens = line.split(",");

                if (tokens.length < 5) {
                    System.out.println("Error parsing.");
                    return null;
                }

                Track track = new Track();

                String title = tokens[TITLE].trim();
                String writer = tokens[WRITER].trim();
                String performer = tokens[PERFORMER].trim();
                int duration = Integer.parseInt(tokens[DURATION].trim());
                int year = Integer.parseInt(tokens[YEAR].trim());

                track.setTitle(title);
                track.setWriter(new Artist(writer));
                track.setDuration(duration);
                track.setYear(year);
                track.setPerformer(new Artist(performer));

                return track;
            }
        }catch (IOException | NullPointerException e){
            System.out.println("Error reading.");
            return null;
        }catch (NumberFormatException e){
            System.out.println("Error parsing.");
            return null;
        }
        return null;
    }
}