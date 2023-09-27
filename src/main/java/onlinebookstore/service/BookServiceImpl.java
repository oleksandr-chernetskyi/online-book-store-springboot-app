package onlinebookstore.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import onlinebookstore.dto.BookDto;
import onlinebookstore.dto.CreateBookRequestDto;
import onlinebookstore.exception.EntityNotFoundException;
import onlinebookstore.exception.IllegalSpecificationArgumentException;
import onlinebookstore.mapper.BookMapper;
import onlinebookstore.model.Book;
import onlinebookstore.repository.SpecificationManager;
import onlinebookstore.repository.book.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final SpecificationManager<Book> specificationManager;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(requestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @ExceptionHandler
    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id));
        return bookMapper.toDto(book);
    }

    @ExceptionHandler
    @Override
    public BookDto update(BookDto bookDto, Long id) {
        Book updatedBook = bookRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find book by id: " + id));
        updatedBook.setTitle(bookDto.getTitle());
        updatedBook.setAuthor(bookDto.getAuthor());
        updatedBook.setIsbn(bookDto.getIsbn());
        updatedBook.setPrice(bookDto.getPrice());
        updatedBook.setDescription(bookDto.getDescription());
        updatedBook.setCoverImage(bookDto.getCoverImage());
        return bookMapper.toDto(bookRepository.save(updatedBook));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> findAllByParams(Map<String, String> parameters, Pageable pageable) {
        Specification<Book> specification = null;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            Specification<Book> spec = specificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(spec) : specification.and(spec);
        }
        if (specification == null) {
            throw new IllegalSpecificationArgumentException(
                    "Invalid combination of filterKey and value");
        }
        return bookRepository.findAll(specification, pageable).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}
