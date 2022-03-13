package Model;
import Model.adt.*;
import Model.stmt.IStmt;
import Model.value.IValue;
import javafx.util.Pair;

import java.io.BufferedReader;

public class PrgState {

    int id;
    static int id_static = 0;
    ISemaphore semaphoreTable;
    IHeap<Integer, IValue> heapTable;
    IDict<String, BufferedReader> fileTable;
    IStack<IStmt> exeStack;
    IDict<String, IValue> symTable;
    IList<IValue> out;
    IStmt originalProgram = null; //optional field, but good to have

    public PrgState(IStack<IStmt> stk, IDict<String, IValue> symtbl,
                    IList<IValue> ot,IDict<String, BufferedReader> ft, IHeap<Integer, IValue> hT, IStmt prg,
                    ISemaphore st){
        synchronized (this){
            id_static++;
            id = id_static;
        }
        heapTable = hT;
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        fileTable = ft;
        semaphoreTable = st;
        stk.push(prg);
    }

    public PrgState oneStep() throws Exception{
        if(exeStack.isEmpty()) throw new Exception("prgstate stack is empty");
        IStmt  crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public int getId(){
        return this.id;
    }

    public PrgState deepCopy()  {
        return new PrgState(exeStack, symTable, out, fileTable, heapTable, originalProgram,
                semaphoreTable);
    }

    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public IStack<IStmt> getExeStack(){
        return exeStack;
    }

    public IHeap<Integer, IValue> getHeapTable(){ return heapTable;}

    public IList<IValue> getOutput() {
        return  out;
    }

    public IDict<String, IValue> getSymTable() {
        return symTable;
    }

    public IDict<String, BufferedReader> getFileTable() { return fileTable; }

    public void setOutput(IList<IValue> output) {
        this.out = output;
    }

    public void setHeapTable(IHeap<Integer, IValue> ht) { this.heapTable = ht; }

    public void setFileTable(IDict<String, BufferedReader> ft) { this.fileTable = ft; }

    public void setSymTable(IDict<String, IValue> SymTable){
        this.symTable = SymTable;
    }

    public void setExeStack(IStack<IStmt> ExeStack){
        this.exeStack = ExeStack;
    }

    public ISemaphore getSemaphoreTable() { return semaphoreTable;}

    public void setSemaphoreTable(IDict<Integer, Pair<Integer, List<Integer>>> semaphoreTable) {
        this.semaphoreTable.setSemaphore(semaphoreTable);
    }
}