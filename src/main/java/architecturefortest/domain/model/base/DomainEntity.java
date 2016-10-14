package architecturefortest.domain.model.base;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Long id;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof DomainEntity) {
            if(this == object) {
                return true;
            }
            DomainEntity other = (DomainEntity) object;
            return other.getId() != null && this.id.equals(other.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
