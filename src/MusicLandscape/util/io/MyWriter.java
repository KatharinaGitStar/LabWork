package MusicLandscape.util.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;

public class MyWriter<T> extends Object {
    protected FileWriter out;
    private MyFormatter<T> theFormat;

    public MyWriter(FileWriter file, MyFormatter<T> theFormat) {
        this.out = file;
        this.theFormat = theFormat;
        if(file == null){
            throw new IllegalArgumentException("expected non-null FileWriter");
        }
        if(theFormat == null){
            throw new IllegalArgumentException("expected non-null MyFormatter");
        }
    }

    public final boolean put(T t){
        try {
            out.write(theFormat.format(t) + System.lineSeparator());
            return true;
        } catch(IOException | IllegalArgumentException e){
            System.out.println("error writing to file: " + e.getMessage());
            return false;
        }
    }

    public void close() throws IOException{
        if(out != null){
            out.close();
        }
    }

}
