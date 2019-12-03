package app.repository;

import app.dto.BookDTO;
import app.dto.BookOrdersDTO;
import app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookById(Long id);

    @Query(value = "select new app.dto.BookOrdersDTO(bo.id, bo.order_date, bo.approved, b.name, u.username) " +
            " FROM BookOrder bo LEFT JOIN Book b on bo.book  = b.id LEFT JOIN User u on bo.user = u.id ")
    List<BookOrdersDTO> findBookOrders();

    @Query(value = "select new app.dto.BookDTO(b.id, b.name, u.id, u.username) " +
            " FROM Book b LEFT JOIN User u on b.user = u.id WHERE u.id <> ?1")
    List<BookDTO> getBooksToOrder(Long id);

    @Query(value = "select new app.dto.BookDTO(b.id, b.name, u.id, u.username) " +
            " FROM Book b LEFT JOIN User u on b.user = u.id WHERE u.id = ?1")
    List<BookDTO> getBooksByUserId(Long id);

    @Query(value = "select new app.dto.BookDTO(b.id, b.name, u.id, u.username) " +
            " FROM Book b LEFT JOIN User u on b.user = u.id")
    List<BookDTO> getAllBooks();

}
