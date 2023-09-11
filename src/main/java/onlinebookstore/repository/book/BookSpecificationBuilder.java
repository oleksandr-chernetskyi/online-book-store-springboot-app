package onlinebookstore.repository.book;

import lombok.RequiredArgsConstructor;
import onlinebookstore.dto.BookSearchParameters;
import onlinebookstore.model.Book;
import onlinebookstore.repository.SpecificationBuilder;
import onlinebookstore.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> specification = Specification.where(null);

        if (searchParameters.titles() != null && searchParameters.titles().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(TITLE)
                    .getSpecification(searchParameters.titles()));
        }

        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(AUTHOR)
                    .getSpecification(searchParameters.authors()));
        }

        if (searchParameters.prices() != null && searchParameters.prices().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(PRICE)
                    .getSpecification(searchParameters.prices()));
        }

        if (searchParameters.descriptions() != null && searchParameters.descriptions().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(DESCRIPTION)
                    .getSpecification(searchParameters.descriptions()));
        }
        return specification;
    }
}
