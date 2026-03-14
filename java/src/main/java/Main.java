public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: provide cargo string, e.g. AABABBAB");
            System.exit(1);
        }
        String cargo = args[0];
        System.out.println("Cargo: " + cargo);
        // TODO: implement solution
    }
}
