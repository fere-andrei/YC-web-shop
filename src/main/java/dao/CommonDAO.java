package dao;

public interface CommonDAO<T> {
    public void saveEntity(T entity);
    public void updateEntity(T entity);
    public void deleteEntity(T entity);
}
