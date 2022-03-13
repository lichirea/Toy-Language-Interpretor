package Model.exp;

import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.VariableException;
import Model.types.IType;
import Model.types.RefType;
import Model.value.IValue;
import Model.value.IntValue;
import Model.value.RefValue;

public class ReadHeapExp extends Exp{
    Exp e;

    public ReadHeapExp(Exp E){
        e = E;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<Integer, IValue> heapTable) throws Exception {
        IValue v = e.eval(symTable, heapTable);
        if (v.getType().getClass() == RefType.class){
            RefValue V = (RefValue) v;
            int addr = V.getAddress();
            if(heapTable.isDefined(addr)){
                return heapTable.get(addr);
            }
            else{
                throw new VariableException("There is nothing at the referenced address");
            }
        }
        else{
            throw new VariableException("Expression was not evaluated to a RefValue");
        }
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typ = e.typecheck(typeEnv);
        if (typ instanceof RefType reft) {
            return reft.getInner();
        } else
            throw new Exception("the rH argument is not a Ref Type");
    }

}
