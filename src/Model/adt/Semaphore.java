package Model.adt;

import javafx.util.Pair;

public class Semaphore implements ISemaphore {
    IDict<Integer, Pair<Integer, List<Integer>>> semaphore;
    static int semaphoreAddress = 1;

    public Semaphore(){
        semaphore = new Dict<>();
    }

    public IDict<Integer, Pair<Integer, List<Integer>>> getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(IDict<Integer, Pair<Integer, List<Integer>>> semaphore) {
        this.semaphore = semaphore;
    }

    public Integer getSemaphorAddress(){
        semaphoreAddress++;
        return semaphoreAddress-1;
    }

    @Override
    public void put(Integer foundIndex, Pair<Integer, List<Integer>> integerListPair) {
        semaphore.add(foundIndex, integerListPair);
    }
}