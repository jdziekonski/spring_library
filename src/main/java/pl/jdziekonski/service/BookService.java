package pl.jdziekonski.service;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jdziekonski.dao.BookDao;
import pl.jdziekonski.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public ArrayList<Item> getAllBooks(){
        return this.bookDao.getAllBooks();
    }

    public Optional<Item> getItemByIsbn(String isbn){ return this.bookDao.getItemByIsbn(isbn); }

    public List<Item> getItemsByCategory(String category){
        return this.bookDao.getItemsByCategory(category);
    }

    public List<Pair<String, Double>> getAuthorsRatings() { return this.bookDao.getAuthorsRatings(); }

}