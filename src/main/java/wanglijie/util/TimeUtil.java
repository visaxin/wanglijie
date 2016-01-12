package wanglijie.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by $Jason.Zhang on 1/5/16.
 */
public class TimeUtil {

    public static String toDateTimeFromUnixTimeStamp(long unixTimeStamp){
        Date date = new Date(unixTimeStamp*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-8")); // give a timezone reference for formating (see comment at the bottom
        return  sdf.format(date);
    }
}
