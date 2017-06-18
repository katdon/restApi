package pl.com.rest.LibraryApplication.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pl.com.rest.LibraryApplication.database.BookDatabase;
import pl.com.rest.LibraryApplication.database.UserDatabase;
import pl.com.rest.LibraryApplication.exception.AppException;
import pl.com.rest.LibraryApplication.model.Book;
import pl.com.rest.LibraryApplication.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kasia on 13.06.2017.
 */

@Path("/books")
@Api(value = "books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResources {

    private static final BookDatabase bookDatabase = new BookDatabase();
    private static final UserDatabase userDatabase = new UserDatabase();

    @GET
    @ApiOperation(value = "See all available books",
            response = Book.class,
            responseContainer = "List")
    public List<Book> getBooks(){
        return bookDatabase.getBooks();
    }

    @POST
    @ApiOperation(value = "Add new book",
            response = Book.class)
    public Response createBook(@Valid Book book){
        Book dbBook = new Book(
                //"",
                book.getTitle(),
                book.getAuthor(),
                book.getDescription()

        );
        return Response.ok(bookDatabase.createBook(dbBook)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{bookId}")
    @ApiOperation(value = "Get one book identified by id",
            response = Book.class)
    public Book getBook(@PathParam("bookId") String bookId) throws AppException {
        Book book = bookDatabase.getBook(bookId);
        if (book == null){
            throw new AppException(404, 990, "Book with id " + bookId + " does not exist", null, null);
        }
        return book;
    }


    @DELETE
    @Path("/{bookId}")
    @ApiOperation(value = "Delete book identified by id")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public void deleteBook(@PathParam("bookId") String bookId)throws AppException  {
        if (bookDatabase.getBook(bookId) == null){
            throw new AppException(404, 990, "Book with id " + bookId + " does not exist", null, null);
        }
        bookDatabase.deleteBook(bookId);
    }

    @PUT
    @Path("/{bookId}")
    @ApiOperation(value = "Update exiting book identified by id")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public Book updateBook(@PathParam("bookId") String bookId, Book book)  throws AppException {
        Book dbBook = bookDatabase.getBook(bookId);
        if (dbBook == null){
            throw new AppException(404, 990, "Book with id " + bookId + " does not exist", null, null);
        }
        book.setId(dbBook.getId());
        bookDatabase.updateBook(book);
        return book;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{bookId}/users")
    @ApiOperation(value = "Show users, who have this book identified by id",
            response = User.class,
            responseContainer = "List")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<User> getUsers(@PathParam("bookId") String bookId) throws AppException{
        Book book = bookDatabase.getBook(bookId);
        if (book == null){
            throw new AppException(404, 990, "Book with id " + bookId + " does not exist", null, null);
        }
        else{
            List<User> tempUsers = userDatabase.getAllUsers();
            List<User> users = new ArrayList<>();
            for (User user : tempUsers){
                if (user.bookIsBorrowed(book)){
                    users.add(user);
                }
            }

            return users;
        }
    }
}
