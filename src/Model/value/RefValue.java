package Model.value;

import Model.types.IType;
import Model.types.RefType;

public class RefValue implements IValue{
    int address;
    IType locationType;

    public RefValue(int address, IType locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress(){
        return address;
    }

    public IType getLocationType(){
        return locationType;
    }

    public IType getType(){
        return new RefType(locationType);
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(address, locationType);
    }

    public void setAddress(int addr) {
        address = addr;
    }
}
