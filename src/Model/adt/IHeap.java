package Model.adt;

import Model.value.IValue;

import java.util.Collection;
import java.util.Map;

public interface IHeap<T1, T2> {
    int getNextAddress();
    void add(T2 value, T1 Addr);
    void update(T1 key, T2 value);
    void remove(T1 key);
    boolean isDefined(T1 key);
    T2 get(T1 key);
    Map<T1, T2> getTable();
    Map<T1, T2> getContent();
    void setContent(Map<T1, T2> map);
}
