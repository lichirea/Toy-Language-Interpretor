package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.IfException;
import Model.exceptions.WhileException;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.value.BoolValue;
import Model.value.IValue;

import java.util.Objects;

public class WhileStmt implements IStmt{
    Exp e;
    IStmt s;

    public WhileStmt(Exp E, IStmt S){
        e = E;
        s = S;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IValue b = e.eval(state.getSymTable(), state.getHeapTable());
        if(Objects.equals(b.getType().toString(), "bool")){
            BoolValue bool = (BoolValue) b;
            if(bool.getValue()){
                state.getExeStack().push(new WhileStmt(e, s));
                state.getExeStack().push(s);
            }
            return null;
        }
        else{
            throw new WhileException("While expression is not a boolean!");
        }
    }

    @Override
    public String toString(){
        return " WHILE("+ e.toString()+") DO(" +s.toString() + ") ";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typexp = e.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            s.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new Exception("The condition of WHILE has not the type bool");
    }
}
