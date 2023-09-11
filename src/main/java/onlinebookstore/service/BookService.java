package onlinebookstore.service;

import java.util.List;
import onlinebookstore.dto.BookDto;
import onlinebookstore.dto.BookSearchParameters;
import onlinebookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto update(BookDto bookDto, Long id);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParameters searchParameters);
}
