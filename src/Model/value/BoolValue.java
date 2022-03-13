package Model.value;

import Model.types.BoolType;
import Model.types.IType;

public class BoolValue implements IValue{

    boolean value;

    public BoolValue(){
        this.value = false;
    }

    public BoolValue(boolean i){
        this.value = i;
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass())
            return false;
        BoolValue o_value = (BoolValue) o;
        return o_value.value == this.value;
    }

    public boolean getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.value ? "true" : "false";
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public IValue deepCopy() {
        return new BoolValue(this.value);
    }
}
