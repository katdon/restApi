package pl.com.rest.LibraryApplication.builder;

import pl.com.rest.LibraryApplication.entity.BookEntityMongo;
import pl.com.rest.LibraryApplication.model.Book;

/**
 * Created by Kasia on 13.06.2017.
 */
public class BookBuilder {

    public static Book build(BookEntityMongo bookEntity, Object id){
        return new Book(id.toString(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getDescription());

    }

    public static Book build(BookEntityMongo bookEntity){
        return new Book(bookEntity.getId().toString(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getDescription());

    }
}
