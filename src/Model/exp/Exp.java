package Model.exp;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.types.IType;
import Model.value.IValue;

public abstract class Exp {

    public abstract IValue eval(IDict<String, IValue> symTable, IHeap<Integer, IValue> heapTable) throws Exception;
    public abstract String toString();
    public abstract IType typecheck(IDict<String,IType> typeEnv) throws Exception;
}
