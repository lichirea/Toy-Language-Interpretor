package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.FileException;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.types.StringType;
import Model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class CloseRFile implements IStmt{
    Exp fileExp;
    StringValue filename;
    BufferedReader reader;

    public CloseRFile(Exp fe) {
        fileExp = fe;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if(Objects.equals(fileExp.eval(state.getSymTable(), state.getHeapTable()).getType().toString(), "string")){
            throw new FileException("CloseRFile Expression is not a string");
        }
        else{
            filename = (StringValue)fileExp.eval(state.getSymTable(), state.getHeapTable());
            if(state.getSymTable().isDefined(filename.getValue())){
                BufferedReader reader = state.getFileTable().lookup(filename.getValue());
                reader.close();
                state.getFileTable().remove(filename.getValue());
                return null;
            }
            else{
                throw new FileException("File is not defined, thus it cannot be closed!");
            }
        }
    }

    @Override
    public String toString(){
        return "CloseRFile("+ fileExp.toString()+")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        IType typexp = fileExp.typecheck(typeEnv);
        if (!typexp.equals(new StringType())) {
            throw new Exception("The FileExp of CloseRFile is not a string!!!!");
        }
        return typeEnv;
    }
}
