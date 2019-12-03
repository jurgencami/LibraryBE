package app.repository;
import app.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    BookOrder findBookOrderByUser_IdAndBook_IdAndApproved(Long user_id, Long book_id, int approved);
    BookOrder findBookOrderById(Long id);
}
