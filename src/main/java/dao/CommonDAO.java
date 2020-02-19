package dao;

public interface CommonDAO<T> {
     void saveEntity(T entity);

     void updateEntity(T entity);

     void deleteEntity(T entity);

}
