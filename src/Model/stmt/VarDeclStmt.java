package Model.stmt;


import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.VariableDuplicateException;
import Model.types.IType;
import Model.value.IValue;

public class VarDeclStmt implements IStmt{
    String name;
    IType type;

    public VarDeclStmt(String name, IType type){
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception{
        if(state.getSymTable().isDefined(name)){
            throw new VariableDuplicateException("Variable name already in use!");
        }
        else{
            state.getSymTable().add(name, type.defaultValue());
        }
        return null;
    }

    @Override
    public String toString(){
        return "declared " + name + " = " + type.toString();
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        typeEnv.add(name,type);
        return typeEnv;
    }


}
