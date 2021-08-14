import refactor.Refactor;

public class Application {
    public static void main(String[] args) {
        Refactor refactor = new Refactor();
        System.out.println(refactor.doRefactor("wqeqweqweqwe {\n"
                + " qweqwe;qweqwe;if(){qweqweqw;}}"));
    }
}
