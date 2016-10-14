package architecturefortest.application.base;

import architecturefortest.domain.model.base.GenericRepository;

public abstract class Persist<T> {

    private GenericRepository<T> genericRepository;

    public Persist(GenericRepository<T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    public void persist(T t) {
        addOtherEntities(t);
        genericRepository.add(t);
    }

    protected void addOtherEntities(T t) {
    }
}
