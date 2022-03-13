package Model.exp;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.InvalidArithmeticException;
import Model.exceptions.ZeroDivException;
import Model.types.IType;
import Model.types.IntType;
import Model.value.IValue;
import Model.value.IntValue;

public class ArithExp extends Exp {
    char op;
    Exp e1, e2;

    //constructor
    public ArithExp(char operator, Exp E1, Exp E2) {
        op = operator;
        e1 = E1;
        e2 = E2;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<Integer, IValue> heapTable) throws Exception {
        IValue v1, v2;
        v1 = e1.eval(symTable, heapTable);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTable, heapTable);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == '+') return new IntValue(n1 + n2);
                if (op == '-') return new IntValue(n1 - n2);
                if (op == '*') return new IntValue(n1 * n2);
                if (op == '/')
                    if (n2 == 0) throw new ZeroDivException("division by zero");
                    else return new IntValue(n1 / n2);
            } else
                throw new InvalidArithmeticException("second operand is not an integer");
        } else
            throw new InvalidArithmeticException("first operand is not an integer");
        return null;
    }

    public char getOp() {
        return this.op;
    }

    public Exp getFirst() {
        return this.e1;
    }

    public Exp getSecond() {
        return this.e2;
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);

        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
            throw new Exception("second operand is not an integer");
        }else
            throw new Exception("first operand is not an integer");
    }
}