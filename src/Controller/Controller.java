package Controller;
import Model.PrgState;
import Model.exceptions.RepoException;
import Model.stmt.IStmt;
import Model.value.RefValue;
import Repository.Repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Model.value.IValue;

public class Controller {

    public ExecutorService executor;
    //constructor
    public Repo r;

    public Controller (Repo rr){
        r = rr;
    }

    public void addProgram(PrgState newPrg) {

        r.addPrg(newPrg);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws Exception{
        prgList.forEach(prg -> {
            try {
                r.logPrgStateExec(prg);
                show(prg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList). stream()
                .map(future -> { try { return future.get();}
                catch(Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }})
                    .filter(p -> p!=null).collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                r.logPrgStateExec(prg);
                show(prg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        r.setPrgList(prgList);
    }


    public void allStep() throws Exception{
        executor = Executors.newFixedThreadPool(2);
        List<PrgState>  prgList=removeCompletedPrg(r.getPrgList());

        List<Integer> l = new ArrayList<Integer>();
        while(prgList.size() > 0){
            prgList.forEach(prg -> {
                try {
                    l.addAll(getAddrFromSymTable(prg.getSymTable().getContent().values()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            prgList.get(0).getHeapTable().setContent(garbageCollector(l,
                    getAddrFromHeap(prgList.get(0).getHeapTable().getContent().values()),
                    prgList.get(0).getHeapTable().getContent()
            ));

            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(r.getPrgList());
        }


         executor.shutdownNow();
         r.setPrgList(prgList);
    }


    public Map<Integer, IValue> garbageCollector(java.util.List<Integer> symTableAddr, java.util.List<Integer> heapAddr, Map<Integer,IValue> heap){
        return heap.entrySet().stream().filter(e->symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public java.util.List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream().filter(v-> v instanceof RefValue).map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();}).collect(Collectors.toList());
    }

    public java.util.List<Integer> getAddrFromHeap(Collection<IValue> heapValues)
    {
        return heapValues.stream().filter(v-> v instanceof RefValue).map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();}).collect(Collectors.toList());
    }

    public String getExeStackString(){
        return r.getPrgList().get(0).getExeStack().toString();
    }

    public String getHeapString(){
        return r.getPrgList().get(0).getHeapTable().toString();
    }

    public List<PrgState> getPrgStates(){
        return r.getPrgList();
    }

    public void show(PrgState p){
        System.out.print(p.getId() + "\n");
        System.out.print("ExeStack: " + p.getExeStack().toString() + "\n");
        System.out.print("SymTable: " + p.getSymTable().toString() + "\n");
        System.out.print("Output: " + p.getOutput().toString() + "\n");
        System.out.print("FileTable: " + p.getFileTable().toString() + "\n");
        System.out.print("HeapTable: " + p.getHeapTable().toString() + "\n");
        System.out.print("SemaphoreTable: " + p.getSemaphoreTable().getSemaphore().toString() + "\n");
        System.out.println();
    }
}
