package Model.stmt;
import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.stmt.IStmt;
import Model.types.IType;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt f, IStmt s){
        first = f;
        snd = s;
    }

    @Override
    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        return snd.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public PrgState execute(PrgState state) throws Exception{
        IStack<IStmt> stk=state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }
}
