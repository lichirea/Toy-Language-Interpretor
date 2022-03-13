package View;

import Model.value.IValue;

public class SymTableElement {
    String name;
    IValue value;
    public SymTableElement(String k, IValue v) {
        name = k;
        value = v;
    }

    public String getName() {
        return name;
    }

    public IValue getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(IValue value) {
        this.value = value;
    }
}
