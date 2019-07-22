package dao.impl;

import com.google.inject.Inject;
import dao.IndexDao;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.ExpressionList;
import io.ebean.Query;
import models.Log;

import javax.persistence.OptimisticLockException;
import java.util.Date;
import java.util.List;

public class IndexDaoImpl implements IndexDao
{
    // private final EbeanServer db;

    @Inject
    public IndexDaoImpl()
    {
        
    }

    public String index()
    {
        return "INDEX FROM DAO";
    }

    @Override
    public List<Log> getWithFilters(List<String> types, List<String> sources, Date from, Date to, Integer offset, Integer limit)
    {
        EbeanServer db = Ebean.getServer("default");

        ExpressionList<Log> expressionList = db.find(Log.class).where();

        if(!types.isEmpty())
        {
            expressionList = expressionList.in("type", types);
        }

        if(!sources.isEmpty())
        {
            expressionList = expressionList.in("source", sources);
        }

        if(null != from)
        {
            expressionList = expressionList.ge("createdAt", from);
        }

        if(null != to)
        {
            expressionList = expressionList.le("createdAt", to);
        }

        Query<Log> query = expressionList.query();

        if(null != offset)
        {
            query = expressionList.setFirstRow(offset);
        }

        if(null != limit)
        {
            query = query.setMaxRows(limit);
        }

        query.orderBy("id desc");

        return query.findList();
    }

    @Override
    public boolean saveLog(Log log)
    {
        EbeanServer db = Ebean.getServer("default");
        boolean isSuccess = false;
        try
        {
            db.save(log);
            isSuccess = true;
        }
        catch(OptimisticLockException ex)
        {

        }

        return isSuccess;
    }
}
