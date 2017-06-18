package pl.com.rest.LibraryApplication.builder;

import pl.com.rest.LibraryApplication.entity.BookEntityMongo;
import pl.com.rest.LibraryApplication.entity.UserEntityMongo;
import pl.com.rest.LibraryApplication.model.Book;
import pl.com.rest.LibraryApplication.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasia on 13.06.2017.
 */
public class UserEntityMongoBuilder {

    public static  UserEntityMongo build(User user, boolean active){
        List<BookEntityMongo> bookEntityList = new ArrayList<>();
        if (user.getBorrowedBooks() != null){

            for( Book book: user.getBorrowedBooks()){
                bookEntityList.add(BookEntityMongoBuilder.buildWithId(book,false));
            }
        }


        return new UserEntityMongo(user.getName(), user.getPesel(), user.getEmail(), user.getPassword(), bookEntityList, active);
    }
}
