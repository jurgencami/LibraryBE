package app.service;

import app.dto.BookDTO;
import app.dto.BookOrdersDTO;
import app.model.Book;
import app.repository.BookRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public List<BookDTO> getBooksByUserId(Long id) {
        return bookRepository.getBooksByUserId(id);
    }

    public List<BookDTO> getBooksByOthers(Long id) {
        return bookRepository.getBooksToOrder(id);
    }

    public List<BookDTO> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public List<BookOrdersDTO> getBookOrderList(){
        return bookRepository.findBookOrders();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }
    public Book update(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    public Book findById(Long id) {
        return bookRepository.findBookById(id);
    }
}
