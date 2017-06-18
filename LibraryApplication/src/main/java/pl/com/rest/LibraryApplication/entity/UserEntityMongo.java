package pl.com.rest.LibraryApplication.entity;

import io.swagger.annotations.Api;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.rest.LibraryApplication.model.Book;


import java.util.Collection;
import java.util.List;

/**
 * Created by Kasia on 13.06.2017.
 */
@Entity("users")
public class UserEntityMongo {

    @Id
    ObjectId id;

    @Property("name")
    private String name;

    @Property("pesel")
    private String pesel;

    @Property("email")
    private String email;

    @Property("password")
    private String password;

    @Reference("borrowedBooks")
    private  List<BookEntityMongo> borrowedBooks;

    private boolean active = false;


    public UserEntityMongo() {
    }

    public UserEntityMongo(String name, String pesel, String email, String password, List<BookEntityMongo>  borrowedBooks, boolean active){
        this.name = name;
        this.pesel = pesel;
        this.email = email;
        this.password = password;
        this.borrowedBooks = borrowedBooks;
        this.active = active;

    }


    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPesel() {
        return pesel;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<BookEntityMongo>  getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean isActive() {
        return active;
    }

    public void setBorrowedBooks(List<BookEntityMongo> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
