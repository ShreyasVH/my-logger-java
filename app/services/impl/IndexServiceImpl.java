package services.impl;

import com.google.inject.Inject;
import dao.IndexDao;
import models.Log;
import services.IndexService;
import skeletons.requests.FilterRequest;
import skeletons.requests.SaveLogRequest;
import utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class IndexServiceImpl implements IndexService
{
    private final IndexDao indexDao;

    @Inject
    public IndexServiceImpl(IndexDao indexDao)
    {
        this.indexDao = indexDao;
    }

    public String index()
    {
//        return "INDEX FROM SERVICE";
        return indexDao.index();
    }

    @Override
    public List<Log> getWithFilters(FilterRequest request)
    {
        List<String> types = new ArrayList<>();
        List<String> sources = new ArrayList<>();
        String from = null;
        String to = null;

        Map<String, List<String>> filters = request.getFilters();
        Map<String, Map<String, String>> rangeFilters = request.getRangeFilters();

        for(String key: filters.keySet())
        {
            List<String> valueList = filters.get(key);

            switch(key)
            {
                case "types":
                    types.addAll(valueList);
                    break;
                case "sources":
                    sources.addAll(valueList);
                    break;
            }
        }

        for(String key: rangeFilters.keySet())
        {
            Map<String, String> valueMap = rangeFilters.get(key);

            switch(key)
            {
                case "createdAt":
                    if(!valueMap.isEmpty())
                    {
                        if(valueMap.containsKey("from"))
                        {
                            from = valueMap.get("from");
                        }

                        if(valueMap.containsKey("to"))
                        {
                            to = valueMap.get("to");
                        }
                    }
                    break;
            }
        }

        return indexDao.getWithFilters(types, sources, from, to, request.getOffset(), request.getCount());
    }

    @Override
    public boolean saveLog(SaveLogRequest request)
    {
        Log log = new Log();
        log.setType(request.getType());
        log.setContent(request.getContent());
        log.setSource(request.getSource());
        log.setCreatedAt(Utils.getCurrentDate());

        return indexDao.saveLog(log);
    }
}
