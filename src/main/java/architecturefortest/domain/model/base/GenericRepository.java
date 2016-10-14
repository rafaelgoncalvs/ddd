package architecturefortest.domain.model.base;

public interface GenericRepository<T> {
    void add(T entidade);
    void remove(T entidade);
    T get(Long id);
}
