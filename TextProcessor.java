import java.util.ArrayList;

public class TextProcessor {
    private ArrayList<String> textList;   // The list of text from the file
    /*
     * Constructor to create a TextProcessor with the specified filename
     */
    public TextProcessor(ArrayList<String> lines) {
        setTextList(lines);
    }
    /*
    * Changes the textList to the newTextList
    */
   public void setTextList(ArrayList<String> newTextList) {
    textList = newTextList;
   }
   
    /*
   * Finds and removes all occurrences of each stop word from textList
   */

  public void removeStopWords(ArrayList<String> stopWords) {
    for (String Stop : stopWords) {
      for (int i = 0; i < textList.size(); i++){
          String line = textList.get(i);
          line = line.replaceAll(" " + Stop + " ", "");
          line = line.replaceAll("" + Stop + "\n", "\n");

          textList.set(i, line);
          }
    }
  }
   /*
   * Returns a String containing the text in textList
   */
  public String toString() {
    String text = "";

    for (String value : textList) {
      text = text + value + "\n";
    }

    return text;
  }






    public static void main(String[] args) {
        ArrayList<String> stopwords = FileOperator.getStringList("stopwords.txt");
        ArrayList<String> posts = FileOperator.getStringList("posts.txt");
        TextProcessor t = new TextProcessor(posts);
        t.removeStopWords(stopwords);
        System.out.println(t.toString());
    }
    
}
