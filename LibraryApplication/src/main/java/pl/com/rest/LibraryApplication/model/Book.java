package pl.com.rest.LibraryApplication.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by Kasia on 13.06.2017.
 */
public class Book implements Serializable {

    private String id;

    @NotNull
    @Size(min = 1 , max = 40)
    private String title;

    @NotNull
    @Size(min = 1 , max = 40)
    private String author;

    private String description;

    public Book() {

    }

    public Book(String id, String title, String author, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public Book(String title, String author, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
