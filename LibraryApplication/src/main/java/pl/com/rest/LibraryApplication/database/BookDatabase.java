package pl.com.rest.LibraryApplication.database;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.com.rest.LibraryApplication.builder.BookBuilder;
import pl.com.rest.LibraryApplication.builder.BookEntityMongoBuilder;
import pl.com.rest.LibraryApplication.entity.BookEntityMongo;
import pl.com.rest.LibraryApplication.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kasia on 13.06.2017.
 */
public class BookDatabase extends MongoDB {

    public Book getBook(String id) {
        try {
            BookEntityMongo bookEntity = getDatastore()
                    .find(BookEntityMongo.class)
                    .field("id")
                    .equal(new ObjectId(id))
                    .get();

            if (bookEntity != null) {
                return BookBuilder.build(bookEntity);
            }

            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


    public List<Book> getBooks(){
        List<Book> lists = new ArrayList<>();
        for (BookEntityMongo bookEntity: getDatastore().find(BookEntityMongo.class)){
            lists.add(BookBuilder.build(bookEntity));
        }
        return lists;
    }

    public Book createBook(Book user) {
        BookEntityMongo userEntity = BookEntityMongoBuilder.build(user,false);
        Key<BookEntityMongo> userEntityMongoKey = getDatastore()
                .save(userEntity);
        return BookBuilder.build(userEntity, userEntityMongoKey.getId());

    }

    public void updateBook(Book book){

        BookEntityMongo bookDb = BookEntityMongoBuilder.build(book, false);
        Query<BookEntityMongo> updateQuery = getDatastore().createQuery(BookEntityMongo.class).field("_id").equal(new ObjectId(book.getId()));
        UpdateOperations<BookEntityMongo> ops = getDatastore().createUpdateOperations(BookEntityMongo.class).set("title", bookDb.getTitle()).set("author", bookDb.getAuthor()).set("description", bookDb.getDescription());
        final UpdateResults results = getDatastore().update(updateQuery, ops);
        System.out.println(results.getWriteResult());


    }

    public void deleteBook(String id){
        getDatastore().delete(BookEntityMongo.class, new ObjectId(id));
    }

}
