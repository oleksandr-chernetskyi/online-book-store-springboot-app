package onlinebookstore.repository.book.specification;

import java.math.BigDecimal;
import java.util.Arrays;
import onlinebookstore.model.Book;
import onlinebookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class LowerPriceSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FILTER_KEY = "minPrice";
    private static final String FIELD_NAME = "price";

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String[] parameters) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_NAME),
                        Arrays.stream(parameters)
                                .map(BigDecimal::new)
                                .min(BigDecimal::compareTo)
                                .orElse(BigDecimal.ZERO)));
    }
}
