import java.util.ArrayList;

public class Storage {
    private final ArrayList<String> greetingsList;
    private final ArrayList<String> quotesList;

    Storage() {
        greetingsList = new ArrayList<>();
        greetingsList.add("Бонжур");
        greetingsList.add("Гуден так");
        greetingsList.add("Хеллоу");
        greetingsList.add("Привет");
        greetingsList.add("Салют");

        quotesList = new ArrayList<>();
        quotesList.add("Be yourself; everyone else is already taken.\n©️Oscar Wilde");
        quotesList.add("So many books, so little time.\n©️ Frank Zappa");
        quotesList.add("Если что-то и стоит делать, так только то, что принято считать невозможным.\n©️Оскар Уайльд");
        quotesList.add("Красота в глазах смотрящего.\n©️Оскар Уайльд");

    }

    private int getIndex(ArrayList<String> arr) {
        return (int) (Math.random() * arr.size());
    }

    String getRandGreeting() {
        return greetingsList.get(getIndex(greetingsList));
    }

    String getRandQuotes() {
        return quotesList.get(getIndex(quotesList));
    }

    public static void main(String[] args) {
        Storage obj = new Storage();
        System.out.print(obj.getRandGreeting());
    }
}
