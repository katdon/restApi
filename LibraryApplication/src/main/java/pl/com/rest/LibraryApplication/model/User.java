package pl.com.rest.LibraryApplication.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kasia on 13.06.2017.
 */
public class User {

    private String id;

    @NotNull
    @Size(min = 1 , max = 40)
    private String name;

    @NotNull
    @Size(min = 10 , max = 12)
    private String pesel;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
    private List<Book>  borrowedBooks;


    public User(String id, String name, String pesel, String email, String password) {
        this.id = id;
        this.name = name;
        this.pesel = pesel;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String pesel, String email, String password, List<Book>  borrowedBooks) {
        this.id = id;
        this.name = name;
        this.pesel = pesel;
        this.email = email;
        this.password = password;
        this.borrowedBooks = borrowedBooks;
    }

    public User(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book>  getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book>  borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }




    public boolean bookIsBorrowed(Book book){
        for(Book b : this.borrowedBooks){
            if(b.getId().contentEquals(book.getId()))
                return true;
        }
        return false;
    }

    public boolean deleteBorrowedBook(String bookId){
        int index=-1;
        for(Book b : this.borrowedBooks){
            if (b.getId().contentEquals(bookId)){
                index = borrowedBooks.indexOf(b);
            }
        }
        if(index!= -1){
            borrowedBooks.remove(index);
            return true;
        }
        else return false;
    }

}
