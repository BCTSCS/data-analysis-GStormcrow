import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleAnalyzer {

    private ArrayList<String> stopWords; //load from FileOperators
    private ArrayList<Article> articles; //load from FileOperators json 
    private static ArrayList<String> words;
    private static ArrayList<Double> values;


    public ArticleAnalyzer(){
        stopWords = FileOperator.getStringList("stopwords.txt");
        System.out.println("Stop Word Count: " + stopWords.size());
        articles = new ArrayList<>();
        System.out.println("Article Count: " + articles.size());
        words = new ArrayList<>();
        values = new ArrayList<>();

    }

    public void addStopWord(String word){
        stopWords.add(word);
    }

    public void addArticle(Article article){
        articles.add(article);
    }

    public Article parseJson(String jsonLine){
        String text = jsonLine;
        // System.out.println("Parsing JSON Line: " + text);
        // Pattern l = Pattern.compile("\"link\":\\s*\"(.*?)\"");
        // Pattern h = Pattern.compile("\"headline\":\\s*\"(.*?)\"");
        // Pattern c = Pattern.compile("\"category\":\\s*\"(.*?)\"");
        // Pattern d = Pattern.compile("\"short_description\":\\s*\"(.*?)\"");
        // Pattern a = Pattern.compile("\"authors\":\\s*\"(.*?)\"");
        // Pattern t = Pattern.compile("\"date\":\\s*\"(.*?)\"");
        Pattern l = Pattern.compile("\"link\":\\s*\"([^\"]+)\"");
        Pattern h = Pattern.compile("\"headline\":\\s*\"([^\"]+)\"");
        Pattern c = Pattern.compile("\"category\":\\s*\"([^\"]+)\"");
        Pattern d = Pattern.compile("\"short_description\":\\s*\"([^\"]+)\"");
        Pattern a = Pattern.compile("\"authors\":\\s*\"([^\"]+)\"");
        Pattern t = Pattern.compile("\"date\":\\s*\"([^\"]+)\"");
        System.out.println("Pattern " + l.pattern());

        Matcher link = l.matcher(text);
        // System.out.println("Matcher " + link.pattern());
        Matcher headline = h.matcher(text);
        // System.out.println("Matcher " + headline.pattern());
        Matcher category = c.matcher(text);
        Matcher description = d.matcher(text);
        Matcher author = a.matcher(text);
        Matcher date = t.matcher(text);
        String linkStr = link.find() ? link.group(1) : "";
        String headlineStr = headline.find() ? headline.group(1) : "";
        String categoryStr = category.find() ? category.group(1) : "";
        String descriptionStr = description.find() ? description.group(1) : "";
        // descriptionStr = this.removeStopWords(descriptionStr);
        String authorStr = author.find() ? author.group(1) : "";
        String dateStr = date.find() ? date.group(1) : "";
        return new Article(linkStr, headlineStr, categoryStr, descriptionStr, authorStr, dateStr);
    } 

    public String removeStopWords(String text){
        for(String stop : stopWords){
            // System.out.println("Removing stop word: " + stop);
            text = text.replaceAll("(?i)\\b" + stop + "\\b", " ");
            // System.out.println("After removing " + stop + ": " + text);
        }
        return text.trim().replaceAll("\\s+", " ");
    }

    public static void main(String[] args) {
        // ArticleAnalyzer analyzer = new ArticleAnalyzer();
        // ArrayList<String> jsonLines = FileOperator.getStringList("News_Category_Dataset_v3.json");
        // for(String line : jsonLines){
        //     Article article = analyzer.parseJson(line);
        //     analyzer.addArticle(article);
        //     // System.out.println("Added Article: " + article);
        // }
        // for(Article line : analyzer.articles){
        //     String cleanedDescription = analyzer.removeStopWords(line.getDescription());
        //     System.out.println("Headline: " + line.getHeadLine());
        //     System.out.println("Description: " + cleanedDescription);
        // }
        // ArticleAnalyzer analyzer = new ArticleAnalyzer();
        // ArrayList<String> jsonLines = FileOperator.getStringList("data.txt");
        // for(String line : jsonLines){
        //     Article article = analyzer.parseJson(line);
        //     analyzer.addArticle(article);
        //     // System.out.println("Added Article: " + article);
        // }
        // for(Article line : analyzer.articles){
        //     String cleanedDescription = analyzer.removeStopWords(line.getDescription());
        //     line.setDescription(cleanedDescription);
        // }
        // for(Article line : analyzer.articles){
        //     System.out.println("Headline: " + line.getHeadLine());
        //     System.out.println("Description: " + line.getDescription());
        // }
        
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
        ArrayList<String> jsonLines = FileOperator.getStringList("sentiments.txt");
        for (String line : jsonLines) {
            Pattern l = Pattern.compile("((?i)[a-z0-9]+),(-?\\d+.\\d+)");  //r write regex to extract the word before, and value after
        // System.out.println(l.pattern());
            Matcher lm =l.matcher(line); //parameter - line of text
        // System.out.println(lm);
        // System.out.println(line);
            boolean found = lm.find(); 
            String word = found ? lm.group(1) : ""; 
            Double value = found ? Double.parseDouble(lm.group(2)) : 0.0;
            System.out.println("Word: " + word);
            System.out.println("Value: " +value);
            analyzer.words.add(word);
            analyzer.values.add(value);
        }   
    }

}
