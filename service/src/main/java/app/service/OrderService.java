package app.service;

import app.exception.AlreadyOrderedBookException;
import app.model.BookOrder;
import app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public BookOrder save(BookOrder order) throws AlreadyOrderedBookException {
        BookOrder existingOrder = orderRepository.findBookOrderByUser_IdAndBook_IdAndApproved(order.getUser().getId(),order.getBook().getId(), order.getApproved());
        if(existingOrder != null){
            throw new AlreadyOrderedBookException("You have already ordered this book!");
        }
        return orderRepository.save(order);
    }
    public BookOrder update(BookOrder order) {

        return orderRepository.save(order);

    }
    public BookOrder getOrderById(Long id){
        return orderRepository.findBookOrderById(id);
    }
}
