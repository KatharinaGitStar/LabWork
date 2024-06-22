package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;

public class MyCSVTrackFormatter extends MyTrackFormatter{

    public MyCSVTrackFormatter() {}

    @Override
    public String format(Track t) {
        if(t == null) return "";

        StringBuilder str = new StringBuilder();
        str.append(t.getTitle());
        str.append(",");
        if(t.getPerformer().getName().equals("Default performer")) {
            str.append("unknown");
        } else {
            str.append(t.getPerformer().getName());
        }
        str.append(",");
        if(t.getWriter().getName().equals("Default writer")) {
            str.append("unknown");
        } else {
            str.append(t.getWriter().getName());
        }
        str.append(",");
        str.append(t.getYear());
        str.append(",");
        str.append(t.getDuration());
        str.append(";");

        return String.valueOf(str);
    }
}
