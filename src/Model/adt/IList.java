package Model.adt;

import Model.stmt.IStmt;

import java.util.ArrayList;

public interface IList<T> {
    void add(T v);
    T pop();
    String toString();
    boolean empty();
    void clear();
    boolean isDefined(T v);
    ArrayList<T> getList();
    int size();
}
