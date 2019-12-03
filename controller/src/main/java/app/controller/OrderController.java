package app.controller;

import app.dto.BookOrderDTO;
import app.exception.AlreadyOrderedBookException;
import app.model.BookOrder;
import app.service.BookService;
import app.service.OrderService;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final BookService bookService;
    //BEING USED
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "add-order", method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody BookOrderDTO order) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(order.getOrderdate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date is empty!");
        }

        BookOrder bookOrder = new BookOrder();
        bookOrder.setBook(bookService.findById(order.getBook_id()));
        bookOrder.setUser(userService.findById(order.getUser_id()));
        bookOrder.setApproved(order.getApproved());
        bookOrder.setOrder_date(localDate);

        try{
            orderService.save(bookOrder);
        }catch (AlreadyOrderedBookException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        return ResponseEntity.ok().build();
    }
    //BEING USED
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "approve-order/{id}")
    public ResponseEntity approve(@PathVariable Long id) {
        BookOrder order = orderService.getOrderById(id);
        order.setApproved(1);
        orderService.update(order);
        return ResponseEntity.ok().build();
    }
    //BEING USED
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "reject-order/{id}")
    public ResponseEntity reject(@PathVariable Long id) {
        BookOrder order = orderService.getOrderById(id);
        order.setApproved(2);
        orderService.update(order);
        return ResponseEntity.ok().build();
    }
}
