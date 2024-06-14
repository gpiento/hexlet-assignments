package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {

        return bookRepository.findAll()
                .stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO findById(final long id) {

        return bookRepository.findById(id)
                .map(bookMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));
    }

    public BookDTO create(final BookCreateDTO bookCreateDTO) {

        Book book = bookMapper.map(bookCreateDTO);
        bookRepository.save(book);

        return bookMapper.map(book);
    }

    public BookDTO update(final long id, final BookUpdateDTO bookUpdateDTO) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));
        bookMapper.update(bookUpdateDTO, book);

        return bookMapper.map(bookRepository.save(book));
    }

    public void delete(final long id) {

        bookRepository.findById(id).ifPresent(bookRepository::delete);
    }

    // END
}
