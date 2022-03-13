package View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SemaphoreTableElement {
    int index;
    int value;
    ArrayList<Integer> values;

    public SemaphoreTableElement(int index, int value, ArrayList<Integer> values) {
        this.index = index;
        this.value = value;
        this.values = values;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public void setValues(ArrayList<Integer> values) {
        this.values = values;
    }
}
