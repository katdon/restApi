package pl.com.rest.LibraryApplication.builder;

import pl.com.rest.LibraryApplication.entity.BookEntityMongo;
import pl.com.rest.LibraryApplication.model.Book;

/**
 * Created by Kasia on 13.06.2017.
 */
public class BookEntityMongoBuilder {

    public static BookEntityMongo build(Book book, boolean active) {

        return new BookEntityMongo(book.getTitle(), book.getAuthor(), book.getDescription(), active);
    }


    public static BookEntityMongo buildWithId(Book book, boolean active) {

        return new BookEntityMongo(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), active);

    }
}
