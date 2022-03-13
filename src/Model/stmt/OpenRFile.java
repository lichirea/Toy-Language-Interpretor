package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.exceptions.FileException;
import Model.exceptions.IfException;
import Model.exp.Exp;
import Model.types.IType;
import Model.types.StringType;
import Model.value.StringValue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Objects;

public class OpenRFile implements IStmt{
    Exp fileExp;
    StringValue filename;
    BufferedReader reader;

    public OpenRFile(Exp fe) {
        fileExp = fe;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if(!Objects.equals(fileExp.eval(state.getSymTable(), state.getHeapTable()).getType().toString(), "string")){
            throw new FileException("OpenRFileExpression is not a string");
        }
        else{
            filename = (StringValue)fileExp.eval(state.getSymTable(), state.getHeapTable());
            if(state.getSymTable().isDefined(filename.getValue())){
                throw new FileException("File already defined!");
            }
            else{
                FileReader in = new FileReader(filename.getValue());
                BufferedReader reader = new BufferedReader(in);
                state.getFileTable().add(filename.getValue(), reader);
                return null;
            }
        }
    }

    @Override
    public String toString(){
        return "openRFile("+ fileExp.toString()+")";
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
