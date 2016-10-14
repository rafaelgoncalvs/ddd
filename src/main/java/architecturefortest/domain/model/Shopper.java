package architecturefortest.domain.model;

import javax.persistence.Entity;

import architecturefortest.domain.model.base.DomainEntity;

@Entity
public class Shopper extends DomainEntity {

    private String name;

    private Shopper() {}

    public Shopper(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
