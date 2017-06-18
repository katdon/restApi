package pl.com.rest.LibraryApplication.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import pl.com.rest.LibraryApplication.entity.BookEntityMongo;
import pl.com.rest.LibraryApplication.entity.UserEntityMongo;
import pl.com.rest.LibraryApplication.model.Book;
import pl.com.rest.LibraryApplication.model.User;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static pl.com.rest.LibraryApplication.database.DatabaseConstant.*;

/**
 * Created by Kasia on 13.06.2017.
 */
public class MongoDB {

    private static Datastore datastore;

    public static Datastore getDatastore() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            morphia.map(UserEntityMongo.class).map(BookEntityMongo.class);
            MongoClient client = createMongoClient();
            datastore = morphia.createDatastore(client, DATABASE);
        }

        return datastore;
    }

    private static MongoClient createMongoClient() {
        MongoClientURI uri =
                new MongoClientURI(
                        String.format(
                                "mongodb://%s:%s@%s:%s/%s",
                                USER_NAME,
                                PASSWORD,
                                HOST,
                                PORT,
                                DATABASE
                        )
                );
        return new MongoClient(uri);


    }
}
