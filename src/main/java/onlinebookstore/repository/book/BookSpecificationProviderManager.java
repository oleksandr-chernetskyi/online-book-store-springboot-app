package onlinebookstore.repository.book;

import java.util.List;
import lombok.RequiredArgsConstructor;
import onlinebookstore.exception.EntityNotFoundException;
import onlinebookstore.model.Book;
import onlinebookstore.repository.SpecificationProvider;
import onlinebookstore.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProvider;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProvider.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find correct specification provider for key: " + key));
    }
}
