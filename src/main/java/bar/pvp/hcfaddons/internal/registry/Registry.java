package bar.pvp.hcfaddons.internal.registry;

import bar.pvp.hcfaddons.internal.mongo.MongoService;
import bar.pvp.hcfaddons.internal.mongo.collection.WrappedCollection;
import bar.pvp.hcfaddons.internal.service.Service;
import bar.pvp.hcfaddons.internal.service.Services;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Getter
public abstract class Registry<T> implements Service {

    protected final WrappedCollection<T> collection = Services.get(MongoService.class).createCollection(getId(), getEntityType());

    //Might be useful to persist really short term data like cacheable players
//    protected final Cache<Object, T> cache = CacheBuilder.newBuilder()
//            .expireAfterWrite(5, TimeUnit.MINUTES) //Remove the cached object 10 minutes after it's loaded
//            .build();

    protected final Map<Object, T> cache = Maps.newHashMap();

    public abstract Class<T> getEntityType();

    public void save(T obj) {
        try {
            collection.save(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Optional<T> get(Object uniqueId) {
        final T cachedObject = cache.get(uniqueId);

        if (cachedObject != null) {
            return Optional.of(cachedObject);
        }

        return collection.load(uniqueId);
    }

    public List<T> loadAll() {
        final List<T> all = collection.all();

        all.forEach(t -> {
            try {
                cache.put(collection.getId(t), t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return all;
    }

    public void remove(Object uniqueId) {
        cache.remove(uniqueId);
    }

    public Optional<T> get(String id, Object object) {
        return collection.loadByEntry(id, object);
    }

    public void persistLocally(T entity) {
        try {
            String id = collection.getId(entity);
            cache.put(UUID.fromString(id), entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
