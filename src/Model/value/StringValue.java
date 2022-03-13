package Model.value;

import Model.types.IType;
import Model.types.StringType;

import java.util.Objects;

public class StringValue implements IValue{
    String value;

    public StringValue(){
        value = "";
    }

    public StringValue(String s){
        value = s;
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass())
            return false;
        StringValue o_value = (StringValue) o;
        return Objects.equals(o_value.value, this.value);
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public IValue deepCopy() {
        return null;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.value.toString();
    }
}
