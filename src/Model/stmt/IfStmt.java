package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.IfException;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.value.BoolValue;

import java.util.Objects;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp=e;
        thenS=t;
        elseS=el;
    }

    @Override
    public String toString(){
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            IDict<String, IType> a = typeEnv.deepCopy();
            thenS.typecheck(typeEnv);
            elseS.typecheck(a);
            return typeEnv;
        }
        else
            throw new Exception("The condition of IF has not the type bool");
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if(Objects.equals(exp.eval(state.getSymTable(), state.getHeapTable()).getType().toString(), "int")){
            throw new IfException("If Expression is not a boolean");
        }
        BoolValue b = (BoolValue) exp.eval(state.getSymTable(), state.getHeapTable());
        if(b.getValue()){
            state.getExeStack().push(thenS);
        }
        else{
            state.getExeStack().push(elseS);
        }
        return null;
    }
}
