package onlinebookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import onlinebookstore.model.Book;
import onlinebookstore.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean isValid(String isbn,
            ConstraintValidatorContext constraintValidatorContext) {
        List<String> isbnList = bookRepository.findAll().stream()
                .map(Book::getIsbn)
                .toList();
        return !isbnList.contains(isbn);
    }
}
