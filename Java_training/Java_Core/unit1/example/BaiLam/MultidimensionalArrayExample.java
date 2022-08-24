package demo.unit1.example.BaiLam;

public class MultidimensionalArrayExample {
    public static void main(String[] args) {
        String[][] values = {{"Doan", "Van", "A"}, {"Tran", "Thi", "B"}};

        for (int i = 0; i < values.length; i++) {
            System.out.print("|");
            for (int j = 0; j < values[i].length; j++) {
                System.out.print(i + " , " + j + "|");
            }
            System.out.println("|");
            for (int j = 0; j < values[i].length; j++) {
                System.out.print(values[i][j] + "|");
            }
            System.out.println();
        }
    }
}
