package demo.unit1.example.BaiLam;

public class StringExample4 {
    public static void main(String[] args) {
        String text = "say hello to everyone";
        System.out.println("length of text is " + text.length());

        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            System.out.println("Charater at " + i + " is " + c);

            i++;
        }

        byte[] bytes = {99, -58, -80, -31, -69, -101, 112, 44, 32, 104, 105, -31, -70, -65, 112, 44, 32, 103, 105, -31, -70, -65, 116};
        try {
            System.out.println(new String(bytes, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
