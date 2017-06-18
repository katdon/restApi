package pl.com.rest.LibraryApplication.database;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import pl.com.rest.LibraryApplication.builder.UserBuilder;
import pl.com.rest.LibraryApplication.builder.UserEntityMongoBuilder;
import pl.com.rest.LibraryApplication.entity.UserEntityMongo;
import pl.com.rest.LibraryApplication.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasia on 13.06.2017.
 */
public class UserDatabase extends MongoDB {

    public User getUser(String id) {
        try {
            UserEntityMongo userEntity = getDatastore()
                    .find(UserEntityMongo.class)
                    .field("id")
                    .equal(new ObjectId(id))
                    .get();

            if (userEntity != null) {
                return UserBuilder.build(userEntity);
            }

            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void updateUser(User user){

        UserEntityMongo userDb = UserEntityMongoBuilder.build(user, false);
        Query<UserEntityMongo> updateQuery = getDatastore().createQuery(UserEntityMongo.class).field("_id").equal(new ObjectId(user.getId()));
        UpdateOperations<UserEntityMongo> ops = getDatastore().createUpdateOperations(UserEntityMongo.class).set("name", userDb.getName()).set("pesel", userDb.getPesel()).set("email", userDb.getEmail()).set("password",userDb.getPassword())
                .set("borrowedBooks", userDb.getBorrowedBooks());
        getDatastore().update(updateQuery, ops);

    }


    public void updateBookUser(User user){

        UserEntityMongo userDb = UserEntityMongoBuilder.build(user, false);
        Query<UserEntityMongo> updateQuery = getDatastore().createQuery(UserEntityMongo.class).field("_id").equal(new ObjectId(user.getId()));
        UpdateOperations<UserEntityMongo> ops = getDatastore().createUpdateOperations(UserEntityMongo.class).set("borrowedBooks", userDb.getBorrowedBooks());
        getDatastore().update(updateQuery, ops);

    }





    public void deleteUser(String id){
        getDatastore().delete(UserEntityMongo.class,new ObjectId(id));
    }
    public User createUser(User user) {
        UserEntityMongo userEntity = UserEntityMongoBuilder.build(user, false);
        Key<UserEntityMongo> userEntityMongoKey = getDatastore()
                .save(userEntity);
        return UserBuilder.build(userEntity, userEntityMongoKey.getId());

    }

    public List<User> getAllUsers(){
        List<User> lists = new ArrayList<>();
        for (UserEntityMongo userEntity: getDatastore().find(UserEntityMongo.class)){
            lists.add(UserBuilder.build(userEntity));
        }
        return lists;
    }
}
