package bar.pvp.hcfaddons.internal.mongo;

import bar.pvp.hcfaddons.internal.mongo.collection.WrappedCollection;
import bar.pvp.hcfaddons.internal.service.Service;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

@Getter
public class MongoService implements Service {

    private final MappingAgent agent = new MappingAgent();

    private final MongoClient client;
    private final MongoDatabase mongo;

    public MongoService(String host, int port, String authUsername, String db, String authPassword, String authDb) {
        client = new MongoClient(
                new ServerAddress(
                        host,
                        port),
//                MongoCredential.createCredential(
//                        authUsername,
//                        authDb,
//                        authPassword.toCharArray()),
                MongoClientOptions.builder().build()
        );

        mongo = client.getDatabase(db);

        log("Connected to mongo database '" + mongo.getName() + "'.");
    }

    public <T> WrappedCollection<T> createCollection(String collection, Class<T> clazz) {
        return new WrappedCollection<>(mongo.getCollection(collection), clazz);
    }

    @Override
    public String getId() {
        return "mongo";
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {
        //Will automatically close
//        client.close();
    }
}
