package Model.adt;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface IDict<T1,T2>{

    void add(T1 v1, T2 v2);
    void update(T1 v1, T2 v2);
    T2 lookup(T1 id);
    boolean isDefined(String id);
    void remove(T1 id);
    String toString();
    Map<T1, T2> getContent();
    void setContent(Map<T1, T2> map);
    IDict<T1, T2> deepCopy();
    Collection<T2> getValues();
    ArrayList<T1> getKeys();
}
