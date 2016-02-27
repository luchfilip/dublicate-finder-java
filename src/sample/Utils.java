package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by luchfilip on 2/27/16.
 */
public class Utils {

    public static String getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o.toString();
            }
        }
        return null;
    }

    public static void printMap(Map mp) {
        for (Object o : mp.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            System.out.println(pair.getKey() + " = " + pair.getValue());

        }
    }

    public static ObservableList getObservableItemsFromMap(Map mp) {
        ObservableList list = FXCollections.observableArrayList();

        for (Object o : mp.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            list.add(pair.getKey() + " = " + pair.getValue());
        }

        return list;
    }

}

