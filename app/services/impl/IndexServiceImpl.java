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
        Date from = null;
        Date to = null;

        Map<String, List<Object>> filters = request.getFilters();
        Map<String, Map<String, Object>> rangeFilters = request.getRangeFilters();

        for(Map.Entry<String, List<Object>> entry: filters.entrySet())
        {
            String key = entry.getKey();
            List<Object> valueList = entry.getValue();

            switch(key)
            {
                case "types":
                    types.addAll(Utils.convertObjectList(valueList, String.class));
                    break;
                case "sources":
                    sources.addAll(Utils.convertObjectList(valueList, String.class));
                    break;
            }
        }

//        for(Map.Entry<String, Map<String, Object>> entry: rangeFilters.entrySet())
//        {
//            String key = entry.getKey();
//            Map<String, Object> valueMap = entry.getValue();
//
//            switch(key)
//            {
//                case "createdDate":
//                    if(!valueMap.isEmpty())
//                    {
//                        if(valueMap.containsKey("from"))
//                        {
//                            from = DateFormat.parse((String) valueMap.get("from"));
//                        }
//                    }
//                    break;
//            }
//        }

        return indexDao.getWithFilters(types, sources, from, to, request.getOffset(), request.getCount());
    }

    @Override
    public boolean saveLog(SaveLogRequest request)
    {
        Log log = new Log();
        log.setType(request.getType());
        log.setContent(request.getContent());
        log.setSource(request.getSource());
        log.setCreatedAt(new Date());

        return indexDao.saveLog(log);
    }
}
