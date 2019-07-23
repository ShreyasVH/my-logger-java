package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Utils
{
    private static final ObjectMapper objMapper = new ObjectMapper();

    public static <T> T convertObject(Object from, Class<T> to) {
        return objMapper.convertValue(from, to);
    }

    public static <T> List<T> convertObjectList(Object from, Class<T> to)
    {
        return objMapper.convertValue(from, TypeFactory.defaultInstance().constructCollectionType(List.class, to));
    }

    public static Date getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));

        return c.getTime();
    }
}
