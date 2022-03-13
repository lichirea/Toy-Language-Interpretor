package Model.value;

import Model.types.IType;

public interface IValue {
    IType getType();
    IValue deepCopy();
}
