public class Main {
    public static void main(String[] args) {
        Race duckRace = new Race();
        duckRace.go();

        Race cheatRace = new Race();
        cheatRace.go(55);
    }
}
