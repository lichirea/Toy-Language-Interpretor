package Model.exp;

import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.InvalidLogicalException;
import Model.types.BoolType;
import Model.types.IType;
import Model.types.IntType;
import Model.value.BoolValue;
import Model.value.IValue;
import Model.value.IntValue;

public class LogicExp extends Exp{
    Exp e1;
    Exp e2;
    char op;

    public LogicExp(char o, Exp E1, Exp E2){
        op = o;
        e1 = E1;
        e2 = E2;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<Integer,IValue> heapTable) throws Exception {
        IValue v1,v2;
        v1 = e1.eval(symTable, heapTable);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(symTable, heapTable);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1,n2;
                n1= i1.getValue();
                n2 = i2.getValue();
                if (op=='a') return new BoolValue(n1 && n2);
                if (op =='o') return new BoolValue(n1 || n2);
                if (op == 'n') return new BoolValue(!n1);
            }else
                throw new InvalidLogicalException("second operand is not a boolean");
        }else
            throw new InvalidLogicalException("first operand is not a boolean");
        return null;
    }

    @Override
    public String toString() {
        if(op == 'a'){
            return (e1 + " AND " + e2);
        }
        else if(op == 'n'){
            return ("NOT " + e1);
        }
        else{
            return (e1 + " OR " + e2);
        }
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);

        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new Exception("second operand is not a bool");
        }else
            throw new Exception("first operand is not a bool");
    }
}
