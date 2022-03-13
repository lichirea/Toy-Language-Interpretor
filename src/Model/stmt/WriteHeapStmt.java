package Model.stmt;
import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.VariableException;
import Model.exp.Exp;
import Model.types.*;
import Model.value.IValue;
import Model.value.RefValue;

public class WriteHeapStmt implements IStmt{
    String var_name;
    Exp e;

    public WriteHeapStmt(String vn, Exp E){
        var_name = vn;
        e = E;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if(state.getSymTable().isDefined(var_name)){
            if(state.getSymTable().lookup(var_name).getType().getClass() == RefType.class){
                RefValue var = (RefValue) state.getSymTable().lookup(var_name);
                if(state.getHeapTable().isDefined(var.getAddress())){
                    IValue val = e.eval(state.getSymTable(), state.getHeapTable());
                    IType locationtype = var.getLocationType();
                    if(locationtype.getClass() == val.getType().getClass()){
                            state.getHeapTable().update(var.getAddress(), val);
                            return null;
                    }
                    else{
                        throw new VariableException("Expression type is not equal to the heap location type");
                    }
                }
                else{
                    throw new VariableException("RefValue is not associated to an address in the heap!!!!!???????????");
                }
            }
            else{
                throw new VariableException("Tried to write to a value that is not RefType");
            }
        }
        else{
            throw new VariableException("Variable not defined");
        }
    }

    @Override
    public String toString(){
        return "heapWrite(" + var_name + ", " + e.toString() + ")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typexp = e.typecheck(typeEnv);
        IType typevar = typeEnv.lookup(var_name);
        if (typevar.equals(new RefType(typexp))) {
            return typeEnv;
        }
        else
            throw new Exception("WriteHeapStmt exp is not a RefType to typevar");
    }
}
