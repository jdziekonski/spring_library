package pl.jdziekonski.controller;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.jdziekonski.entity.Item;
import pl.jdziekonski.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<Item> getAllBooks() { return bookService.getAllBooks(); }

    @RequestMapping(value="/{isbn}", method = RequestMethod.GET)
    public Optional<Item> getItemByIsbn(@PathVariable("isbn") String isbn){
        return bookService.getItemByIsbn(isbn);
    }

    @RequestMapping(value="/category/{category}", method = RequestMethod.GET)
    public List<Item> getItemsByCategory(@PathVariable("category") String category){ return bookService.getItemsByCategory(category); }

    @RequestMapping(value="/ratings", method = RequestMethod.GET)
    public List<Pair<String, Double>> getAuthorsRatings() { return this.bookService.getAuthorsRatings(); }

}