import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * represents a text parser which takes a user input, splits it into its parts, and counts relevant statistics about it
 */
public class Main {
    private Scanner kboard = new Scanner(System.in).useDelimiter("~~~");
    private String in;
    private List<String> sections = new ArrayList<>();
    private List<Integer> sectionPeriodCounts = new ArrayList<>();
    private List<Integer> sectionQuestionCounts = new ArrayList<>();
    private List<Integer> sectionExclamationCounts = new ArrayList<>();
    
    // MODIFIES: this
    // EFFECTS: gathers user data, parses it and prints required data.
    private Main() {
        this.in = kboard.next();
        extractSections();
        countSectionsSentences();
        printFindings();
    }
    
    // REQUIRES: in !empty
    // MODIFIES: this
    // EFFECTS: extracts the sections of the text based on numerical section headers.
    private void extractSections() {
        int cursor = 2;
        int nextSectionNum = 2;
        while (cursor < in.length()) {
            int nextSectionStart = in.indexOf(nextSectionNum + ".", cursor);
            if (nextSectionStart == -1) {
                nextSectionStart = in.length() - 1;
            }
            sections.add(in.substring(cursor, nextSectionStart));
            cursor = nextSectionStart + 3;
            nextSectionNum++;
        }
    }
    
    // MODIFIES: this
    // EFFECTS: counts and stores . ? ! counts per section in respective lists.
    private void countSectionsSentences() {
        for (String section : sections) {
            sectionPeriodCounts.add(section.length() - section.replace(".", "").length());
            sectionQuestionCounts.add(section.length() - section.replace("?", "").length());
            sectionExclamationCounts.add(section.length() - section.replace("!", "").length());
        }
    }
    
    
    // EFFECTS: prints findings in format to be transfer to spreadsheet.
    //              <fragment #> <. count> <? count> <! count> <fragment length>
    private void printFindings() {
        System.out.println();
        for (int i = 0; i < sections.size(); i++) {
            // System.out.println(sections.get(i));
            
            System.out.println((i +  1)
                    + "\t" + sectionPeriodCounts.get(i)
                    + "\t" + sectionQuestionCounts.get(i)
                    + "\t" + sectionExclamationCounts.get(i)
                    + "\t" + sections.get(i).length());
        }
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
