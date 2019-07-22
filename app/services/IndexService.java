package services;

import models.Log;
import skeletons.requests.FilterRequest;
import skeletons.requests.SaveLogRequest;

import java.util.List;

public interface IndexService
{
    String index();

    List<Log> getWithFilters(FilterRequest request);

    boolean saveLog(SaveLogRequest request);
}
