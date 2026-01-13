public class Article{
    private String link;
    private String headline;
    private String category;
    private String description;
    private String author;
    private String date;

    public Article(String link, String headline, String category, String description, String author, String date){
        this.link = link;
        this.headline = headline;
        this.category = category;
        this.description = description;
        this.author = author;
        this.date = date;
    }

    public String toString(){
        return "headline: " + headline + ", description: " + description;
    }
    public String getLink(){
        return link;
    }
    public String getHeadLine(){
        return headline;
    }
    public String getCategory(){
        return category;
    }
    public String getDescription(){
        return description;
    }
    public String getAuthor(){
        return author;
    } 
    public String getDate(){
        return date;
    }
    public void setLink(String link){
        this.link = link;
    }
    public void setHeadLine(String headline){
        this.headline = headline;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setDate(String date){
        this.date = date;
    }
    public static void main(String[] args) {
    }
}