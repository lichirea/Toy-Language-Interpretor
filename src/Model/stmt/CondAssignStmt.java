package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.value.IValue;

public class CondAssignStmt implements IStmt{
    Exp exp1, exp2, exp3;
    String var;


    public CondAssignStmt(String v, Exp e1, Exp e2, Exp e3) {
        var = v;
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
    }

    @Override
    public String toString(){
        return var+"="+exp1.toString()+"?"+exp2.toString()+":"+exp3.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stk=state.getExeStack();
        stk.push(new IfStmt(exp1, new AssignStmt(var, exp2), new AssignStmt(var, exp3)));
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typexp1 = exp1.typecheck(typeEnv);
        if (typexp1.equals(new BoolType())) {
            IType typevar = typeEnv.lookup(var);
            IType typexp2 = exp2.typecheck(typeEnv);
            IType typexp3 = exp3.typecheck(typeEnv);
            if (typevar.equals(typexp2))
                if (typevar.equals(typexp3))
                    return typeEnv;
                else
                    throw new Exception("Third expression doesn't match with variable");
            else
                throw new Exception("Second expression doesn't match with variable");
        }
        else
            throw new Exception("The first exp of Conditional Assignment doesn't have the type bool");

    }
}
