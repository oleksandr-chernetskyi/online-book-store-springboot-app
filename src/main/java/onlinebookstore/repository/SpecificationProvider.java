package onlinebookstore.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    Specification<T> getSpecification(String[] parameters);

    String getKey();
}
