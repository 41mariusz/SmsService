package Lists;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static List<sms> firstSmsList = new ArrayList<>();
    private static List<sms> secondSmsList = new ArrayList<>();
    private static List<sms> compareSmsList = new ArrayList<>();

    public boolean firstAddSms(sms sms) {
        return firstSmsList.add(sms);
    }

    public List<sms> firstGetSmsList() {
        return firstSmsList;
    }

    public sms firstDelSms(int id) {
        return firstSmsList.remove(id);
    }

    public void firstSetSmsList(List<sms> smsList) {
        this.firstSmsList = smsList;
    }

    public boolean secondAddSms(sms sms) {
        return secondSmsList.add(sms);
    }

    public List<sms> secondGetSmsList() {
        return secondSmsList;
    }

    public sms secondDelSms(int id) {
        return secondSmsList.remove(id);
    }

    public void secondSetSmsList(List<sms> smsList) {
        this.secondSmsList = smsList;
    }

    public boolean compareAddSms(sms sms) {
        return compareSmsList.add(sms);
    }

    public List<sms> compareGetSmsList() {
        return compareSmsList;
    }

    public sms compareDelSms(int id) {
        return compareSmsList.remove(id);
    }

    public void compareSetSmsList(List<sms> smsList) {
        this.compareSmsList = smsList;
    }

    private static String urlSmsService() {
        return "https://miracletele.com/sms/receive/PL";
    }

    private static void checkInbox(boolean type) {
        Document document = null;

        try {
            document = Jsoup.connect(urlSmsService()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements messages = document.select("div#online-sms-container > div");

        if(!type) secondSmsList.clear();

        for (Element message : messages) {
            Document doc = Jsoup.parse(message.outerHtml());
            Element fromElement = doc.select(".phone-answer").first();
            Element textElement = doc.select(".sms-text").first();
            Element timeElement = doc.select(".received-sms-ago").first();

            String from = fromElement.text();
            String text = textElement.text();
            long time = Long.parseLong(timeElement.attr("data-moment"));

            if (type) firstSmsList.add(new sms(from, text, time));
            else secondSmsList.add(new sms(from, text, time));
        }
    }

    public static boolean checkNewMessage() {
        if(firstSmsList == secondSmsList) return false;

        return true;
    }

    public static void main(String[] args) {
        checkInbox(false);
        System.out.println("List size: " + firstSmsList.size());
        for (int i = 0; i < firstSmsList.size(); i++) {
            System.out.println("[" + i + "] " + firstSmsList.get(i).getNumber() + " :: " + firstSmsList.get(i).getMessage() + " :: " + firstSmsList.get(i).getTimestamp());
        }
        System.out.println("\n---\n");
        for (int i = 0; i < secondSmsList.size(); i++) {
            System.out.println("[" + i + "] " + secondSmsList.get(i).getNumber() + " :: " + secondSmsList.get(i).getMessage() + " :: " + secondSmsList.get(i).getTimestamp());
        }
    }
}
