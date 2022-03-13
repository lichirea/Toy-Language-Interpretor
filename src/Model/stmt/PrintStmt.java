package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IList;
import Model.adt.List;
import Model.exp.Exp;
import Model.types.IType;
import Model.value.IValue;

public class PrintStmt implements IStmt{
    Exp expression;

    @Override
    public String toString(){
        return "print(" +expression.toString()+")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        expression.typecheck(typeEnv);
        return typeEnv;
    }

    public PrintStmt(Exp exp){
        this.expression = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception{
        IList<IValue> output = state.getOutput();
        output.add(expression.eval(state.getSymTable(), state.getHeapTable()));
        state.setOutput(output);
        return null;
    }
}
