package Model.value;

import Model.types.IType;
import Model.types.IntType;

public class IntValue implements IValue{

    int value;

    public IntValue(){
        this.value = 0;
    }

    public IntValue(int i){
        this.value = i;
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass())
            return false;
        IntValue o_value = (IntValue) o;
        return o_value.value == this.value;
    }

    @Override
    public String toString(){
        return Integer.toString(this.value);
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(this.value);
    }
}
