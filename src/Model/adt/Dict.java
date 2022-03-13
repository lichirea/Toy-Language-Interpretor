package Model.adt;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Dict<T1,T2> implements IDict<T1,T2> {
    Map<T1, T2> dictionary;

    public Dict() {
        dictionary = new HashMap<T1,T2>();
    }

    public Collection<T2> getValues(){
        return dictionary.values();
    }

    @Override
    public ArrayList<T1> getKeys() {
        return new ArrayList<T1>(dictionary.keySet());
    }

    public String toString(){
        return dictionary.toString();
    }

    @Override
    public Map<T1, T2> getContent() {
        return this.dictionary;
    }

    @Override
    public void setContent(Map<T1, T2> map) {
        dictionary.putAll(map);
    }

    @Override
    public IDict<T1, T2> deepCopy() {
        IDict<T1, T2> copy = new Dict<T1, T2>();
        copy.setContent(dictionary);
        return copy;
    }


    @Override
    public void add(T1 v1, T2 v2) {
        dictionary.put(v1, v2);
    }

    @Override
    public void update(T1 v1, T2 v2) {
        ///change this to a replace if u want the references to follow, same in the heap but i didnt write there
        dictionary.put(v1, v2);
    }

    @Override
    public T2 lookup(T1 id) {
        return dictionary.get(id);
    }

    @Override
    public boolean isDefined(String id) {
        return dictionary.containsKey((T1) id);
    }

    @Override
    public void remove(T1 id) {
        dictionary.remove(id);
    }
}
