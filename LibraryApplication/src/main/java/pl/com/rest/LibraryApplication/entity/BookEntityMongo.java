package pl.com.rest.LibraryApplication.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import pl.com.rest.LibraryApplication.model.User;

import java.util.Collection;
import java.util.List;


/**
 * Created by Kasia on 13.06.2017.
 */

@Entity("books")
public class BookEntityMongo {

    @Id
    ObjectId id;

    @Property("title")
    private String title;

    @Property("author")
    private String author;

    @Property("description")
    private String description;


    @Indexed
    private boolean active = false;


    public BookEntityMongo() {
    }

    public BookEntityMongo(String title, String author, String description, boolean active) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.active = active;
    }

    public BookEntityMongo(String id, String title, String author, String description,  boolean active) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.active = active;
        this.id = new ObjectId(id);
    }




    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
