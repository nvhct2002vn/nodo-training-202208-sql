package demo.unit1.example.BaiLam;

public class ArrayExample {

    public static void main(String[] args) {
        bai8();
    }

    public static void bai8() {
        float[] values = {4.5f, 5.5f};

        float total = 0;
        for (float value : values) {
            total += value;
        }
        System.out.println("The total value of array is " + total);
    }

    public static void bai9() {
        float[] values = {1.2f, 3.1f, 5.1f, 1.6f, 9};
        float total = 0;

        for (float value : values) {
            total += value;
        }
        System.out.println("Total value: " + total);

        float max = 0;
        for (int i = 0; i < values.length; i++) {
            if (max < values[i]) {
                max = values[i];
            }
        }
        System.out.println("Max value: " + max);
    }
}
