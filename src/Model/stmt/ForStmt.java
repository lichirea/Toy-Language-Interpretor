package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exp.Exp;
import Model.exp.RelationalExp;
import Model.exp.VarExp;
import Model.types.BoolType;
import Model.types.IType;
import Model.types.IntType;

public class ForStmt implements IStmt{
    String var;
    Exp  exp1, exp2, exp3;
    IStmt s;

    public ForStmt(String var, Exp exp1, Exp exp2, Exp exp3, IStmt ss) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        s =ss;
    }



    @Override
    public PrgState execute(PrgState state) throws Exception {
        state.getExeStack().push(new CompStmt(new AssignStmt("var", exp1),
                new WhileStmt(new RelationalExp("<", new VarExp("var"), exp2),
                        new CompStmt(s, new AssignStmt("var", exp3)))));
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typexp3 = exp3.typecheck(typeEnv);
        if (typexp3.equals(new IntType())) {
            IType typexp2 = exp2.typecheck(typeEnv);
            if (typexp2.equals(new IntType())) {
                IType typexp1 = exp1.typecheck(typeEnv);
                if (typexp1.equals(new IntType())) {
                    s.typecheck(typeEnv);
                    return typeEnv;
                }
                else
                    throw new Exception("EXP1 has not the type int");
            }
            else
                throw new Exception("EXP2 has not the type int");
        }
        else
            throw new Exception("EXP3 has not the type int");
    }
}
