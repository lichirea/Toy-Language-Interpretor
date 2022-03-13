package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.exceptions.VariableException;
import Model.exp.Exp;
import Model.types.IType;
import Model.value.IValue;

public class AssignStmt implements IStmt{

    String id;
    Exp expression;

    public AssignStmt(String id, Exp exp){
        this.id = id;
        this.expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stk=state.getExeStack();
        IDict<String, IValue> symTbl= state.getSymTable();
        if (symTbl.isDefined(id)) {
            IValue val = expression.eval(symTbl, state.getHeapTable());
            IType typId = (symTbl.lookup(id)).getType();
            IType valType = val.getType();
            if (valType.equals(typId))
                symTbl.update(id, val);
            else
                throw new VariableException("declared type of variable" + id + " and type of the assigned expression do not match");
        }
        else throw new VariableException("the used variable" +id + " was not declared before");
            return null;
    }

    @Override
    public String toString(){
        return this.id + "=" + this.expression.toString();
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typevar = typeEnv.lookup(id);
        IType typexp = expression.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new Exception("Assignment: right hand side and left hand side have different types ");
    }
}
