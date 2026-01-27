public class ProductReview{
    private String name;
    private String review;

    public ProductReview(String name, String pReview){
        this.name = name;
        this.review = pReview;
    }

    public String getName(){
        return name;
    }
    public String getReview(){
        return review;
    }
}