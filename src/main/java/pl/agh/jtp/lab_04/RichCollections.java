package pl.agh.jtp.lab_04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class RichCollections<A, B> {

    /**
     * Function maps given collection according to given function Function.apply(A a).
     * @param collection Given Collection<A>
     * @param function  Given Function, must implement interface Function(must have method apply(A a)).
     * @param <A>
     * @param <B>
     * @return  Mapped Collection<B>
     */
    public static  <A, B> Collection<B> map(Collection<A> collection, Function<A, B> function) {
        Collection<B> result = new ArrayList<B>();
        for(A element : collection) {
            result.add(function.apply(element));
        }
        return result;
    }


    /**
     * Static method which filter given collection in view of object's class.
     * If there are any object of wanted class it returns empty collection.
     * @param collection Given collection
     * @param type  Class which instances should stay in returned collection.
     * @param <A>
     * @param <B>
     * @return  Filtered collection with object of given class.
     */
    @SuppressWarnings("unchecked")
    public static <A, B> Collection<B> filter(Collection<A> collection, Class<B> type) {
        Collection<B> result = new ArrayList<B>();
        for(A element : collection) {
            if(type.isInstance(element)) {
                result.add((B) element);
            }
        }

        return result;
    }

    /**
     * <pre>
     * Static method filter given collection according to condition given in argument.
     * In returned collection are only that elements from given collection which granded the condition.
     * </pre>
     * @param collection  Given collection
     * @param predicate  Predicate which provide the condition
     * @param <A>
     * @return  Filtered collection
     */
    public static <A> Collection<A> filter(Collection<A> collection, Predicate<A> predicate) {
        Collection<A> result = new ArrayList<A>();
        for(A element : collection) {
            if(predicate.apply(element)) {
                result.add(element);
            }
        }

        return result;
    }

    /**
     * <pre>
     * Static method groups given collection into map.
     * Map keys are provided from Function, value of the key is a collection of objects witch the same key.
     * </pre>
     * @param collection Given collection
     * @param function  Function which defines the key of argument
     * @param <A>
     * @param <B>
     * @return  Mapped collection.
     */
    public static <A, B> Map<A, Collection<B>> groupBy(Collection<B> collection, Function<B, A> function) {
        Map<A, Collection<B>> result = new HashMap<A, Collection<B>>();
        for(B element : collection) {
            A key = function.apply(element);

            if(result.containsKey(key)){
                Collection<B> coll = result.get(key);
                coll.add(element);
            } else {
                Collection<B> collectionForValue = new ArrayList<B>();
                collectionForValue.add(element);
                result.put(key, collectionForValue);
            }
        }

        return result;
    }

}
