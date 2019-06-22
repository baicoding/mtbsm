package org.msgtu.common.utils;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class SequenceUtil {
    // 支持根据表名生成值
    private static Map<String, Integer> serials = new java.util.concurrent.ConcurrentHashMap<String, Integer>();

    // 如果没有找到，取默认值
    private static int defalutSerial = 0;

    public static synchronized String getSequenceID(String table) {
        if (table == null) {
            table = "";
        }
        table = table.toUpperCase();
        Integer value = serials.get(table);
        int serial = 0;
        if (value == null) {
            if (defalutSerial == 9999) {
                defalutSerial = 0;
            } else {
                defalutSerial += 1;
            }
            serials.put(table, defalutSerial);
            serial = defalutSerial;
        } else {
            if (value == 9999) {
                value = 0;
            } else {
                value += 1;
            }
            serials.put(table, value);
            serial = value;

        }

        String stampString = EpsDateUtils
                .getCurrentTime(EpsDateUtils.FORMAT_YYYYMMDDHHMMSS);

        String serialStr = String.valueOf(serial);

        String msgid = table + stampString
                + repeat(4 - serialStr.length(), '0') + serialStr;
        return msgid;
    }

    private static String repeat(int times, char c) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < times; ++i) {
            buf.append(c);
        }
        return buf.toString();
    }

    public static synchronized String getAccessCode() {

        Random ra = new Random();
        String[] str = {"2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
                "D", "E", "F", "G", "H", "J", "K", "M", "N", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d",
                "e", "f", "g", "h", "i", "j", "k", "m", "n", "o", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        int accCodeLen = 8;
        StringBuffer accessCodeTemp = new StringBuffer();

        accessCodeTemp.append("");
        int index = 0;
        while (index < accCodeLen) {
            accessCodeTemp.append(str[ra.nextInt(str.length)]);
            index++;
        }

        String accessCode = accessCodeTemp.toString();

        return accessCode;
    }

    private static RandomId ran = new RandomId();

    public static synchronized String getRandomId() {
        return ran.randomId();
    }

    static class RandomId {
        private Random random;

        private String table;

        public RandomId() {
            random = new Random();
            table = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            // table =
            // "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        }

        public String randomId() {
            int id = random.nextInt(1000);
            String ret = null, num = String.format("%05d", id);
            int key = random.nextInt(10), seed = random.nextInt(100);
            Caesar caesar = new Caesar(table, seed);
            num = caesar.encode(key, num);
            ret = num + String.format("%01d", key)
                    + String.format("%02d", seed);

            return ret;
        }

        // public static void main(String[] args)
        // {
        // RandomId r = new RandomId();
        // for (int i = 0; i < 2; i += 1)
        // {
        // System.out.println(r.randomId(i));
        // }
        // }
    }

    public static String getRandCharId() {
        String[] str = { // "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                // "A","B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replaceAll("\\-", "");
        String r = "";
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (c <= 'z' && c >= 'a') {
                r += (char) (c + 'A' - 'a');
            } else {
                Random rd = new Random();
                int j = rd.nextInt(2);
                if (j == 0) {
                    r += str[Integer.valueOf(String.valueOf(c)) + 10];
                } else {
                    r += str[Integer.valueOf(String.valueOf(c))];
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(getSequenceID(""));
        System.out.println(getRandomId());
        for (int i = 0; i < 20; i++) {
            String r = getRandCharId();

            System.out.println(r);
        }
    }
}
