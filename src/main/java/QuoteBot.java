import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class QuoteBot extends TelegramLongPollingBot {
    private final String BOT_TOKEN = BuildConfig.BOT_TOKEN;
    Storage storage = new Storage();


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = 0;
            String userName = null;

            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();

            String receivedMessage = update.getMessage().getText();
            if (update.getMessage().hasText()) {
                botAnswerUtils(receivedMessage, chatId, userName);
            }
        }

    }


    private void startBot(long chatId, String userName) {

        SendMessage ans = new SendMessage();
        ans.setChatId(chatId);
        ReplyKeyboardMarkup replyKeyboardMarkup = getKeyboard("Хочу фразу");

        if (replyKeyboardMarkup != null) {
            ans.setReplyMarkup(replyKeyboardMarkup);
        }

        ans.setText(String.format("%s %s!", storage.getRandGreeting(), userName));

        try {
            execute(ans);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendRandQuote(long chatId) {
        SendMessage ans = new SendMessage();
        ans.setChatId(chatId);

        ans.setText(String.format("%s", storage.getRandQuotes()));

        try {
            execute(ans);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void iDontUnderstand(long chatId) {

        SendMessage ans = new SendMessage();
        ans.setChatId(chatId);
        ans.setText("Я Вас не понимаю...");

        ReplyKeyboardMarkup replyKeyboardMarkup = getKeyboard("Хочу фразу");

        if (replyKeyboardMarkup != null) {
            ans.setReplyMarkup(replyKeyboardMarkup);
        }

        try {
            execute(ans);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void iDontUnderstand(long chatId, String text) {

        SendMessage ans = new SendMessage();
        ans.setChatId(chatId);
        ans.setText(String.format("%s%s", "Я Вас не понимаю", text));


        ReplyKeyboardMarkup replyKeyboardMarkup = getKeyboard("Хочу фразу");

        if (replyKeyboardMarkup != null) {
            ans.setReplyMarkup(replyKeyboardMarkup);
        }

        try {
            execute(ans);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {

        if (receivedMessage.equals("/start")) {
            startBot(chatId, userName);
        }
        else if (receivedMessage.equals("Хочу фразу")) {
            sendRandQuote(chatId);

        } else {
            iDontUnderstand(chatId, "\uD83D\uDE48");
        }
    }

    private static ReplyKeyboardMarkup getKeyboard(String keyboardText) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(keyboardText);
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    @Override
    public String getBotUsername() {
        return "Amazing Java";
    }

    @Override
    public String getBotToken() {
        return this.BOT_TOKEN;
    }

}
