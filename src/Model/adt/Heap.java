package Model.adt;


import Model.types.RefType;
import Model.value.IValue;
import Model.value.IntValue;
import Model.value.RefValue;

import java.lang.reflect.Array;
import java.util.*;

public class Heap<T1, T2> implements IHeap<T1, T2> {


    Map<T1, T2> heapTable;
    int nextAddress = 0;

    public Heap() {
        heapTable = new HashMap<T1, T2>();
    }


    public String toString(){
        return heapTable.toString();
    }

    @Override
    public int getNextAddress(){
        nextAddress += 1;
        return nextAddress;
    }

    @Override
    public void add(T2 value, T1 Addr) {
        this.heapTable.put(Addr, value);
    }

    public void setHeapTable(Map<T1, T2> hp){
        this.heapTable = hp;
    }

    @Override
    public Map<T1, T2> getContent() {
        return this.heapTable;
    }

    public void setContent(Map<T1, T2> map){
        this.heapTable = map;
    }

    @Override
    public void update(T1 key, T2 value)
    {
        heapTable.put(key, value);
    }

    public Map<T1, T2> getHeapTable() {
        return heapTable;
    }

    @Override
    public void remove(T1 key){
        heapTable.remove(key);
    }

    @Override
    public boolean isDefined(T1 key) {
        return heapTable.containsKey(key);
    }

    @Override
    public T2 get(T1 key) {
        return heapTable.get(key);
    }

    @Override
    public Map<T1, T2> getTable() {
        return this.heapTable;
    }

}
