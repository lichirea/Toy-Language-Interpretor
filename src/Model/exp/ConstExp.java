package Model.exp;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.types.IType;
import Model.value.IValue;

public class ConstExp extends Exp{
    IValue val;

    public ConstExp(IValue v){
        val = v;
    }
    @Override
    public IValue eval(IDict<String,IValue> symTable, IHeap<Integer, IValue> heapTable) {
        return val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws Exception {
        return val.getType();
    }
}
