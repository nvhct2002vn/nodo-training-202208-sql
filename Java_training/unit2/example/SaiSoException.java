package demo.unit2.example;

public class SaiSoException extends Exception {
    private String so;

    public SaiSoException(String value) {
        this.so = value;
    }

    public String getMessage() {
        return so + " Khong phai la so";
    }
}
