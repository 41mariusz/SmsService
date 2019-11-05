package Lists;

import java.util.ArrayList;
import java.util.List;

    public class SmsManager {

        public List<sms> smsList;

        public SmsManager() {
            this.smsList = new ArrayList<>();
            smsList.add(new sms("Telefon", "samsung s10e", 1212));
            smsList.add(new sms("Laptop", "acer", 3199));
            smsList.add(new sms("Monitor", "iiyama", 2289));
            smsList.add(new sms("Klawiatura", "logitech", 4321));
        }

        public boolean addSms(sms sms) {
            return smsList.add(sms);
        }

        public List<sms> getSmsList() {
            return smsList;
        }

        public sms delSms(int id) {
            return smsList.remove(id);
        }

        public void setSmsList(List<sms> smsList) {
            this.smsList = smsList;
        }
    }
