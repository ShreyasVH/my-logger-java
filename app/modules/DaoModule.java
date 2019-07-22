package modules;

import com.google.inject.AbstractModule;
import dao.IndexDao;
import dao.impl.IndexDaoImpl;

public class DaoModule extends AbstractModule
{
    @Override
    public void configure()
    {
        bind(IndexDao.class).to(IndexDaoImpl.class).asEagerSingleton();
    }
}
