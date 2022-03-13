package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exp.Exp;
import Model.exp.LogicExp;
import Model.types.BoolType;
import Model.types.IType;

public class RepeatUntilStmt implements IStmt{
    IStmt stmt;
    Exp exp;

    public RepeatUntilStmt(IStmt stmt, Exp exp) {
        this.stmt = stmt;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        state.getExeStack().push(new CompStmt(stmt, new WhileStmt(new LogicExp('n', exp, exp), stmt)));
        return null;
    }

    @Override
    public String toString(){
        return "REPEAT("+stmt.toString()+")UNTIL("+exp.toString()+")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new Exception("The condition of REPEAT UNTIL has not the type bool");
    }
}
