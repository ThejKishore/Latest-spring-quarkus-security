package org.kish.learning;

import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;
import org.kish.learning.model.Book;
import org.kish.learning.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/hello")
public class ExampleResource {


    @Autowired
    private BookRepo bookRepo;

    @Value("${greeting.msg}")
    private String msg;

    @Value("${greeting.user.msg}")
    private String usermsg;

    @GetMapping
    @Secured("user")
    public String hello() {
        return String.format(" %s ",msg);
    }

    @GetMapping("/{user}")
    @Secured("admin")
    public String helloUser(@PathVariable("user") String user){
        return String.format("%s %s",usermsg,user);
    }


    @PostMapping("/book")
    public Book saveBook(Book book){
       log.info(" incoming request : {} ",book);
       return  bookRepo.save(book);
    }

    @GetMapping("/book")
    public Iterable<Book> getBooks(){
        return  bookRepo.findAll();
    }
}