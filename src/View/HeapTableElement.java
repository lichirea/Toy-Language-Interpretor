package View;

public class HeapTableElement {

    int address;
    String value;
    public HeapTableElement(int a, String v){
        address = a;
        value = v;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getValue() {
        return value;
    }

    public int getAddress() {
        return address;
    }
}
