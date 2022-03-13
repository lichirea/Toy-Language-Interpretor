package Model.stmt;
import Model.PrgState;
import Model.adt.IDict;
import Model.types.IType;

public interface IStmt {
    PrgState execute(PrgState state) throws Exception;
    String toString();
    IDict<String, IType> typecheck(IDict<String,IType> typeEnv) throws Exception;
}
