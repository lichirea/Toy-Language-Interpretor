package Model.exp;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.types.IType;
import Model.value.IValue;

public class VarExp extends Exp{
    String id;

    public VarExp(String id){
        this.id = id;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<Integer, IValue> heapTable) {
        return symTable.lookup(id);
    }

    @Override
    public String toString() {return id;}

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws Exception {
        return typeEnv.lookup(id);
    }



}
