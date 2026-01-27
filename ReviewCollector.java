
import java.util.ArrayList;

public class ReviewCollector{
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;
    
    public ReviewCollector(){
        reviewList = new ArrayList<>();
        productList = new ArrayList<>();
    }
    public void addReview(ProductReview prodReview){
        reviewList.add(prodReview);
    }
    public int getNumGoodReviews(String prodname){
        int count = 0;
    }
}