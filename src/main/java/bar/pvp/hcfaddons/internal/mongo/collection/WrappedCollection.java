package bar.pvp.hcfaddons.internal.mongo.collection;

import bar.pvp.hcfaddons.internal.mongo.MongoService;
import bar.pvp.hcfaddons.internal.mongo.model.EntityID;
import bar.pvp.hcfaddons.internal.service.Services;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Data;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
public class WrappedCollection<T> {

    private static final MongoService SERVICE = Services.get(MongoService.class);

    private final MongoCollection<Document> collection;
    private final Class<T> clazz;

    private Field idField;

    public WrappedCollection(MongoCollection<Document> collection, Class<T> clazz) {
        this.collection = collection;
        this.clazz = clazz;

        init();
    }

    private void init() {
        //Sets the idField
        final List<Field> fields = Lists.newArrayList(
                clazz.getDeclaredFields()
        );
        fields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));

        for (Field declaredField : fields) {
            if (declaredField != null && declaredField.isAnnotationPresent(EntityID.class)) {
                this.idField = declaredField;
                break;
            }
        }
    }

    public String getId(T entity) throws IllegalAccessException {
        idField.setAccessible(true);
        return idField.get(entity).toString();
    }

    public void save(T entity) throws IllegalAccessException {
        try {
            collection.replaceOne(Filters.eq(idField.getName(), getId(entity)), SERVICE.getAgent().map(entity), new UpdateOptions().upsert(true));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        try {
            collection.deleteOne(Filters.eq(idField.getName(), getId(entity)));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Optional<T> load(Object id) {
        final Document document = collection.find(Filters.eq(idField.getName(), id.toString())).first();

        if (document == null) {
            return Optional.empty();
        }

        return Optional.of(SERVICE.getAgent().fromMapped(document, clazz));
    }

    public List<T> all() {
        final List<T> toReturn = Lists.newArrayList();

        for (Document document : collection.find()) {
            toReturn.add(SERVICE.getAgent().fromMapped(document, clazz));
        }

        return toReturn;
    }

    public Optional<T> loadByEntry(String id, Object object) {
        final Document document = collection.find(Filters.eq(id, object)).first();

        if (document == null) {
            return Optional.empty();
        }

        return Optional.of(SERVICE.getAgent().fromMapped(document, clazz));
    }
}