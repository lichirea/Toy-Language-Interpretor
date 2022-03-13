package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.VariableException;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.types.RefType;
import Model.value.IValue;
import Model.value.RefValue;

import java.util.Objects;

public class AllocateHeapStmt implements IStmt{
    String var_name;
    Exp e;

    public AllocateHeapStmt(String vn, Exp E){
        var_name = vn;
        e = E;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if(state.getSymTable().isDefined(var_name)){
            IType var_type = state.getSymTable().lookup(var_name).getType();
            IType reftype = new RefType(new BoolType());
            if(var_type.getClass().equals(reftype.getClass())){
                IValue eval = e.eval(state.getSymTable(), state.getHeapTable());
                RefValue x = (RefValue) state.getSymTable().lookup(var_name);
                IType locationType = x.getLocationType();
                if(eval.getType().getClass() == locationType.getClass()){
                    int addr = state.getHeapTable().getNextAddress();
                    RefValue rv = new RefValue(addr, x.getLocationType());
                    state.getHeapTable().add(eval, addr);
                    state.getSymTable().update(var_name, rv);
                }
                else{
                    throw new VariableException("the used variable " + var_name + " was attempted to be defined in the heap with a wrong type");
                }
            }
            else{
                throw new VariableException("the used variable " + var_name +
                        " isn't declared as a reference and was attempted to be allocated");
            }
        }
        else throw new VariableException("the used variable " + var_name + " was not declared before");
        return null;
    }

    @Override
    public String toString(){
        return "*" + this.var_name + "=" + this.e.toString();
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typevar = typeEnv.lookup(var_name);
        IType typexp = e.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new Exception("AllocateHeap stmt: right hand side and left hand side have different types ");
    }
}
