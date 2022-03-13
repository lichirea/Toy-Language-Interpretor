package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.List;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.value.IValue;
import Model.value.IntValue;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphoreStmt implements IStmt{
    private String var;
    private Exp expression;
    private static Lock lock = new ReentrantLock();

    public CreateSemaphoreStmt(String var, Exp expression){
        this.var = var;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        lock.lock();
        IDict<String, IValue> symbolTable = state.getSymTable();
        IDict<Integer, Pair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable().getSemaphore();

        IntValue iv = (IntValue) expression.eval(symbolTable, state.getHeapTable());
        Integer value = iv.getValue();
        Integer location = state.getSemaphoreTable().getSemaphorAddress();
        semaphoreTable.add(location, new Pair<Integer, List<Integer>>(value, new List<Integer>()));
        symbolTable.add(var, new IntValue(location));

        state.setSemaphoreTable(semaphoreTable);
        state.setSymTable(symbolTable);
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newSemaphore( " + var + ", " + expression.toString() + ")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typevar = typeEnv.lookup(var);
        IType typeexp = expression.typecheck(typeEnv);
        if (typeexp.equals(typevar)){
            return typeEnv;
        }
        else
            throw new Exception("Create Semaphore error: variable doesnt have the same type as expression");
    }
}
