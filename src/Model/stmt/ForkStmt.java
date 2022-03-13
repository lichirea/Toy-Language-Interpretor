package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.adt.MyStack;
import Model.types.BoolType;
import Model.types.IType;

public class ForkStmt implements IStmt{

    IStmt stmt;

    public ForkStmt(IStmt s){
        stmt = s;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyStack<IStmt> newStack = new MyStack<IStmt>();
        return new PrgState(newStack, state.getSymTable().deepCopy(), state.getOutput(), state.getFileTable(), state.getHeapTable(), stmt, state.getSemaphoreTable());
    }

    @Override
    public String toString(){
        return "FORK(" + stmt.toString() + ")ENDFORK";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        stmt.typecheck(typeEnv);
        return typeEnv;
    }
}
