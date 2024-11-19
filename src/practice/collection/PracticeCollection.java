package practice.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticeCollection {

    @SuppressWarnings("unchecked")
    public static <T> T[] filter(T[] array, Filter filter){
        List<T> result = new ArrayList<>();
        for (T item : array){
            T filteredItem = (T) filter.apply(item);
            if(filteredItem != null){
                result.add(filteredItem);
            }
        }
        T[] resultArray = (T[]) Array.newInstance(array.getClass().getComponentType(), result.size());
        return result.toArray(resultArray);
    }

    public static <T> Map<T, Integer> countElements(T[] array){
        HashMap<T, Integer> result = new HashMap<>();
        for (T item: array) {
            result.put(item, countOccurrences(array, item));
        }
        return result;
    }

    private static <T> int countOccurrences(T[] array, T element) {
        int count = 0;
        for (T item : array) {
            if (item.equals(element)) {
                count++;
            }
        }
        return count;
    }
}
