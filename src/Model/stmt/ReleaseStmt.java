package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.List;
import Model.types.IType;
import Model.types.IntType;
import Model.value.IntValue;
import javafx.util.Pair;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public ReleaseStmt(String var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        lock.lock();
        try{
            IDict<Integer, Pair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable().getSemaphore();

            IntValue aux = (IntValue) state.getSymTable().lookup(var);
            int foundIndex = aux.getValue();

            if(Objects.isNull(foundIndex))
                throw new Exception("No such variable in symbolTable");
            Pair<Integer, List<Integer>> semaphoreValue = semaphoreTable.lookup(foundIndex);
            List<Integer> threads = semaphoreValue.getValue();
            Integer nMax = semaphoreValue.getKey();
            if(threads.isDefined(state.getId()))
                threads.remove(state.getId());
            state.getSemaphoreTable().put(foundIndex, new Pair<>(nMax, threads));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return null;
    }

    @Override
    public String toString() {
        return "release( " + var + " )";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typevar = typeEnv.lookup(var);
        if (typevar.equals(new IntType())){
            return typeEnv;
        }
        else
            throw new Exception("Release Semaphore error: variable doesnt have the int type");
    }
}
