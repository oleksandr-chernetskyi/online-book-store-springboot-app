package onlinebookstore.service;

import java.util.List;
import java.util.Map;
import onlinebookstore.dto.BookDto;
import onlinebookstore.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto update(BookDto bookDto, Long id);

    void deleteById(Long id);

    List<BookDto> findAllByParams(Map<String, String> parameters, Pageable pageable);
}
