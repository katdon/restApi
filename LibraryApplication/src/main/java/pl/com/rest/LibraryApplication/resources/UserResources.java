package pl.com.rest.LibraryApplication.resources;

import io.swagger.annotations.*;
import pl.com.rest.LibraryApplication.database.UserDatabase;
import pl.com.rest.LibraryApplication.exception.AppException;
import pl.com.rest.LibraryApplication.model.Book;
import pl.com.rest.LibraryApplication.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasia on 13.06.2017.
 */

@Path("/users")
@Api(value = "/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {

    private static final UserDatabase database = new UserDatabase();

    @GET
    @ApiOperation(value = "See all available users",
            response = User.class,
            responseContainer = "List")
    public List<User> getAllUsers(){
        return database.getAllUsers();
    }


    @POST
    @ApiOperation(value = "Add new user", notes = "You can provide dog and existing visited places",
            response = User.class)
    public User createUser(@Valid User user)  {
        User dbUser = new User(
                "",
                user.getName(),
                user.getPesel(),
                user.getEmail(),
                user.getPassword(),
                user.getBorrowedBooks()
        );
        return database.createUser(dbUser);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    @ApiOperation(value = "Get one user identified by id",
            response = User.class)
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public User getUser(@PathParam("userId") String userId) throws AppException {
        User user = database.getUser(userId);
        if (user == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        return user;
    }


    @PUT
    @Path("/{userId}")
    @ApiOperation(value = "Edit user")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public User updateUser(
            @ApiParam (value = "Id of user to fetch", required = true)@PathParam("userId") String userId, User user) throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        user.setId(dbuser.getId());
        database.updateUser(user);
        return user;
    }

    @DELETE
    @Path("/{userId}")
    @ApiOperation(value = "Delete user identified by id")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public void deleteUser(@PathParam("userId") String userId)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        database.deleteUser(userId);
    }

    @GET
    @Path("/{userId}/books")
    @ApiOperation(value = "Show all users books")
    @ApiResponses( value = {
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Book> getUsersBooks(@PathParam("userId") String userId)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        List <Book> booksList = dbuser.getBorrowedBooks();
        return booksList;

    }

    @POST
    @Path("/{userId}/books")
    @ApiOperation(value = "Add new book to user")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Book> addUsersBook(@PathParam("userId") String userId,@Valid Book book)throws AppException {
        User dbuser = database.getUser(userId);
        if (dbuser == null) {
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        List <Book> booksList = new ArrayList<>();
        if(dbuser.getBorrowedBooks()!=null){
            booksList = dbuser.getBorrowedBooks();
        }
        booksList.add(book);
        dbuser.setBorrowedBooks(booksList);
        database.updateBookUser(dbuser);
        return booksList;
    }




    @GET
    @Path("/{userId}/borrowed")
    @ApiOperation(value = "Show all visited places by user")
    public List<Book> getBorrowedBooks(@PathParam("userId") String userId)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        List <Book> bookList = dbuser.getBorrowedBooks();
        return bookList;

    }



}
