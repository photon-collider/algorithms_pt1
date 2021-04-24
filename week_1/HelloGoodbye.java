public class HelloGoodbye {
    public static void main(String args[]) {

        String name_1 = args[0];
        String name_2 = args[1];

        String out_1 = String.format("Hello %s and %s.", name_1, name_2);
        String out_2 = String.format("Goodbye %s and %s.", name_2, name_1);

        System.out.println(out_1);
        System.out.println(out_2);

    }
}
