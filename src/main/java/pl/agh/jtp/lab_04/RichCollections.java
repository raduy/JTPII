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
        Map<A, B> result = new HashMap<A, B>();
        for(A element : collection) {
            result.put(element, function.apply(element));
        }
        return result.values();
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
}
