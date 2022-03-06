package pl.jdziekonski.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import pl.jdziekonski.entity.Book;
import pl.jdziekonski.entity.Item;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

@Repository
public class BookDao {

    private ObjectMapper mapper = new ObjectMapper();
    private Book books;
    private List<Pair<String, Double>> authorsWithRating = new ArrayList<>();
    private ArrayList<Item> items;
    private InputStream file;
    {
        try {
            file = new ClassPathResource("books.json").getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            books = mapper.readValue(file, Book.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> getAllBooks() {
        return this.books.getItems();
    }

    public Optional<Item> getItemByIsbn(String isbn) {
        items = this.books.getItems();
        return items.stream()
                .filter((item) -> item.getVolumeInfo().getIndustryIdentifiers()!=null)
                .filter(item -> item.getVolumeInfo().getIndustryIdentifiers()
                        .stream()
                        .anyMatch(sub_item -> sub_item.getType().contains("ISBN_13")
                                && sub_item.getIdentifier().contains(isbn)
                                || item.getId().contains(isbn)))
                .findAny();
    }

    public List<Item> getItemsByCategory(String category) {
        items = this.books.getItems();
        return items.stream()
                .filter((item) -> item.getVolumeInfo().getCategories()!=null
                    && item.getVolumeInfo().getCategories().contains(category))
                .collect(Collectors.toList());
    }

    private final Comparator<Pair<String, Double>> reverseComp = reverseOrder(comparing(Pair::getValue));

    public List<Pair<String, Double>> getAuthorsRatings() {
        authorsWithRating.clear();

        items = this.books.getItems();
        int bookQuantity = 0;
        Double jAuthorRating = 0.0;

        List<String> ratedAuthorsList = items
                .stream()
                .filter(item -> item.getVolumeInfo().getAverageRating()!=null)
                .flatMap(f_item -> f_item.getVolumeInfo().getAuthors().stream())
                .map(Object::toString)
                .collect(Collectors.toList());

        for (String author : ratedAuthorsList) {
            for (Item item : items) {
                List<String> authors = item.getVolumeInfo().getAuthors();
                if (authors != null) {
                    for (int k = 0; k < item.getVolumeInfo().getAuthors().size(); k++) {
                        if (author.contentEquals(item.getVolumeInfo().getAuthors().get(k))) {
                            Double foundAverageRating = item.getVolumeInfo().getAverageRating();
                            jAuthorRating = (jAuthorRating + foundAverageRating);
                            bookQuantity++;
                        }
                    }
                }
            }
            jAuthorRating = jAuthorRating / bookQuantity;
            authorsWithRating.add(new Pair<>(author, jAuthorRating));
            jAuthorRating = 0.0;
            bookQuantity = 0;
        }

        return authorsWithRating
                .stream()
                .sorted(reverseComp)
                .collect(Collectors.toList());
    }
}