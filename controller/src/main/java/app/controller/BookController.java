package app.controller;

import app.dto.BookDTO;
import app.dto.BookOrdersDTO;
import app.model.Book;
import app.service.BookService;
import app.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@ComponentScan({"app.service"})
public class BookController {
    private static final Logger logger = LogManager.getLogger(ControllerApplication.class);
    private final BookService bookService;
    private final UserService userService;
    //BEING USED
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @GetMapping("books")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookDTO>> findAll() {
        logger.info("Controller findAll was called");
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //BEING USED
    @GetMapping("books/{userid}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookDTO>> findById(@PathVariable Long userid) {
        List<BookDTO> list = bookService.getBooksByUserId(userid);
        logger.info("Controller findById was called");
        return ResponseEntity.ok(list);
    }

    @GetMapping("order-book-list/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookDTO>> findByOthers(@PathVariable Long id) {
        List<BookDTO> list = bookService.getBooksByOthers(id);
        logger.info("Controller findByOthers was called");
        return ResponseEntity.ok(list);
    }

    //BEING USED
    @GetMapping("orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookOrdersDTO>> findOrdered() {
        List<BookOrdersDTO> list = bookService.getBookOrderList();
        return ResponseEntity.ok(list);
    }

    //BEING USED
    @PostMapping
    @RequestMapping(value = "add-book")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity create(@Valid @RequestBody BookDTO book) {
        Book newBook = new Book();
        newBook.setName(book.getName());
        newBook.setUser(userService.findById(book.getUser_id()));
        bookService.save(newBook);
        logger.info("Controller create was called");
        return ResponseEntity.ok().build();
    }

    //BEING USED
    @PostMapping
    @RequestMapping(value = "update-book")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity update(@Valid @RequestBody BookDTO book) {
        Book nbook = bookService.findById(book.getId());
        nbook.setName(book.getName());
        bookService.update(nbook);
        return ResponseEntity.ok().build();
    }

    //BEING USED
    @GetMapping
    @RequestMapping(value = "delete-book/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
