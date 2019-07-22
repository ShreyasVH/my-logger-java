package dao;

import models.Log;

import java.util.Date;
import java.util.List;

public interface IndexDao
{
    String index();

    List<Log> getWithFilters(List<String> types, List<String> sources, Date from, Date to, Integer offset, Integer limit);

    boolean saveLog(Log log);
}
