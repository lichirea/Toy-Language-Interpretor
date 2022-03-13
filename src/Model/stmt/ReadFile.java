package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.FileException;
import Model.exp.Exp;
import Model.types.IType;
import Model.types.StringType;
import Model.value.IntValue;

import java.io.BufferedReader;
import java.util.Objects;

public class ReadFile implements IStmt{
    Exp var_file_id;
    String id;
    String var_name;

    public ReadFile(Exp id, String name) {
        var_file_id=id;
        var_name=name;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if(state.getSymTable().isDefined(var_name)){
            if(Objects.equals(var_file_id.eval(state.getSymTable(), state.getHeapTable()).getType().toString(), "string")){
                id = var_file_id.eval(state.getSymTable(), state.getHeapTable()).toString();
                BufferedReader bf = state.getFileTable().lookup(id);
                String n = bf.readLine();
                int x;
                if (Objects.equals(n, "")){
                    x = 0;
                }
                else{
                    x = Integer.parseInt(n);
                }
                state.getSymTable().add(var_name, new IntValue(x));
                return null;
            }
            else{
                throw new FileException("Expression not a string!!!");
            }
        }
        else{
            throw new FileException("Variable not defined!");
        }
    }

    @Override
    public String toString(){
        return "readFile("+var_file_id.toString()+","+var_name+")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typexp = var_file_id.typecheck(typeEnv);
        if (!typexp.equals(new StringType())) {
            throw new Exception("The FileExp of CloseRFile is not a string!!!!");
        }
        return typeEnv;
    }
}
