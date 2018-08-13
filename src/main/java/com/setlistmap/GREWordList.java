package com.setlistmap;

import java.util.HashMap;
import java.util.Map;

public class GREWordList {
    static Map<String, Word> wordList = new HashMap<>();

    public static void main(String[] args) {
        wordList.put("abate", new Word("verb", "subside", "aliennate,increase,extend,amplify,continue,enlarge"));
        wordList.put("abate2", new Word("verb", "subside", "aliennate,increase,extend,amplify,continue,enlarge"));
        wordList.put("abate3", new Word("verb", "subside", "aliennate,increase,extend,amplify,continue,enlarge"));
        printList();
        System.out.println("\n查询abate :" + wordList.get("abate"));
        wordList.remove("abate2");
        System.out.println("\n然后:");
        printList();
    }

    static private void printList() {
        System.out.println("\n打印字典:");
        for (Map.Entry<String, Word> entry : wordList.entrySet()) {
            String key = entry.getKey();
            Word value = entry.getValue();
            System.out.println("key="+key+",value="+value);
        }
    }
    private static class Word{
        private String type;
        private String synonym;
        private String antonym;

        public Word(String type, String synonym, String antonym) {
            this.type = type;
            this.synonym = synonym;
            this.antonym = antonym;
        }

        public String getType() {
            return type;
        }

        public String getSynonym() {
            return synonym;
        }

        public String getAntonym() {
            return antonym;
        }

        @Override
        public String toString() {
            return "["+type+"; "+synonym+";"+antonym+"]";
        }
    }
}
