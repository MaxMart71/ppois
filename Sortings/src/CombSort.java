import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CombSort<T extends Comparable<T>> {

    public List<T> sortByIncrease(List<T> list, Comparator<T> comparator) {
        int gap = list.size();
        boolean swapped = true;

        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / 1.3);
            }
            swapped = false;
            for (int i = 0; i + gap < list.size(); i++) {
                if (comparator.compare(list.get(i), list.get(i+gap)) > 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(i + gap));
                    list.set(i + gap, temp);
                    swapped = true;
                }
            }
        }
        return list;
    }
    public List<T> sortByDecrease(List<T> list, Comparator<T> comparator){
        return sortByIncrease(list,comparator).reversed();
    }
}