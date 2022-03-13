package Model.adt;

import javafx.util.Pair;

public interface ISemaphore {
    void setSemaphore(IDict<Integer, Pair<Integer, List<Integer>>> semaphore);
    IDict<Integer, Pair<Integer, List<Integer>>> getSemaphore();
    Integer getSemaphorAddress();

    void put(Integer foundIndex, Pair<Integer, List<Integer>> integerListPair);
}