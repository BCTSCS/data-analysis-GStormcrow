public class ProductReview{
    private String name;
    private String review;

    public ProductReview(String name, String review){
        this.name = name;
        this.review = review;
    }

    public String getName(){
        return name;
    }
    public String getReview(){
        return review;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setReview(String review){
        this.review = review;
    }
}