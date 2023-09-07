package onlinebookstore.repository;

import java.util.List;
import java.util.Optional;
import onlinebookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> getAll();

    Optional<Book> getById(Long id);
}
