package onlinebookstore.repository.book.specification;

import java.util.Arrays;
import onlinebookstore.model.Book;
import onlinebookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    private static final String TITLE = "title";

    @Override
    public Specification<Book> getSpecification(String[] parameters) {
        return ((root, query, criteriaBuilder) -> root
                .get(TITLE).in(Arrays.stream(parameters).toArray()));
    }

    @Override
    public String getKey() {
        return TITLE;
    }
}
