package Model.types;

import Model.value.IValue;
import Model.value.RefValue;

public class RefType  implements IType{
    IType inner;

    public RefType(IType inner){
        this.inner = inner;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof RefType){
            return ((RefType) obj).getInner().equals(this.inner);
        }
        return false;
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0, this.inner);
    }

    @Override
    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public IType deepCopy() {
        return new RefType(this.inner);
    }
}
