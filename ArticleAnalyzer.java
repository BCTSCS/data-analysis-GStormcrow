import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleAnalyzer {

    private ArrayList<String> stopWords; //load from FileOperators
    private ArrayList<Article> articles; //load from FileOperators json 

    public ArticleAnalyzer(){
        stopWords = FileOperator.getStringList("stopwords.txt");
        System.out.println("Stop Word Count: " + stopWords.size());
        articles = new ArrayList<>();
        System.out.println("Article Count: " + articles.size());
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
        Pattern l = Pattern.compile("\"link\": \"(.*?)\"");
        Pattern h = Pattern.compile("\"headline\": \"(.*?)\"");
        Pattern c = Pattern.compile("\"category\": \"(.*?)\"");
        Pattern d = Pattern.compile("\"short_description\": \"(.*?)\"");
        Pattern a = Pattern.compile("\"authors\": \"(.*?)\"");
        Pattern t = Pattern.compile("\"date\": \"(.*?)\"");
        // System.out.println("Pattern " + l.pattern());

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
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
        ArrayList<String> jsonLines = FileOperator.getStringList("News_Category_Dataset_v3.json");
        for(String line : jsonLines){
            Article article = analyzer.parseJson(line);
            analyzer.addArticle(article);
            // System.out.println("Added Article: " + article);
        }
        for(Article line : analyzer.articles){
            String cleanedDescription = analyzer.removeStopWords(line.getDescription());
            System.out.println("Headline: " + line.getHeadLine());
            System.out.println("Description: " + cleanedDescription);
        }
    }

}
