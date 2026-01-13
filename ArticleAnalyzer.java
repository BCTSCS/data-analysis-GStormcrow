import java.util.ArrayList;
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

    }

    public void addArticle(Article article){

    }

    public Article parseJson(String jsonLine){
        Article result;
        String text = jsonLine;
        Pattern l = Pattern.compile("re");
        Pattern h = Pattern.compile("re");
        Pattern c = Pattern.compile("re");
        Pattern d = Pattern.compile("re");
        Pattern a = Pattern.compile("re");
        Pattern t = Pattern.compile("re");
        return null;
    } 

    public String removeStopWords(String text){
        return null;
    }

    public static void main(String[] args) {
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
    }

}
