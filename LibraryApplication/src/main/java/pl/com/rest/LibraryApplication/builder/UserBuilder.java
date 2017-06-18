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
public class UserBuilder {

    public static User build(UserEntityMongo userEntity, Object id){
        List<Book> bookList = new ArrayList<>();
        if (userEntity.getBorrowedBooks() != null){
            for(BookEntityMongo bookEntityMongo: userEntity.getBorrowedBooks()){
                bookList.add(BookBuilder.build(bookEntityMongo));
            }
        }

        return new User(id.toString(), userEntity.getName(), userEntity.getPesel(), userEntity.getEmail(), userEntity.getPassword(), bookList);
    }

    public static User build(UserEntityMongo userEntity){
        List<Book> bookList = new ArrayList<>();
        if (userEntity.getBorrowedBooks() != null){
            for( BookEntityMongo bookEntityMongo: userEntity.getBorrowedBooks()){
                bookList.add(BookBuilder.build(bookEntityMongo));
            }
        }

        return new User(userEntity.getId().toString(), userEntity.getName(), userEntity.getPesel(), userEntity.getEmail(), userEntity.getPassword(), bookList);
    }
}
