package lk.ijse.dep.web.lms.dao;

import lk.ijse.dep.web.lms.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO<T extends SuperEntity, PK extends Serializable> extends SuperDAO {

    void save(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(PK key) throws Exception;

    T get(PK key) throws Exception;

    List<T> getAll() throws Exception;
}
