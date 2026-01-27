import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewCollector{
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;
    
    public ReviewCollector(){
        reviewList = new ArrayList<>();
        productList = new ArrayList<>();
    }
    public void addReview(ProductReview prodReview){
        reviewList.add(prodReview);
        productList.add(prodReview.getName());
    }
    public int getNumGoodReviews(String prodname){
        int count = 0;
        for(ProductReview review : reviewList){
            if(review.getName().equals(prodname)){
                String reviewText = review.getReview().toLowerCase();
                if(reviewText.contains("best")){
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args){
        ReviewCollector collector = new ReviewCollector();
        ArrayList<String> reviews = FileOperator.getStringList("product.txt");
        System.out.println(reviews);
        for(String line : reviews){
            Pattern n = Pattern.compile("Name:\\s*(.*?)\\s*Review:\\s*(.*)");
            Matcher m = n.matcher(line);
            if(m.find()){
                String name = m.group(1);
                String review = m.group(2);
                ProductReview prodReview = new ProductReview(name, review);
                collector.addReview(prodReview);
            }
        }
        for(ProductReview review : collector.reviewList){
            System.out.print(collector.getNumGoodReviews(review.getName()) + " good reviews for " + review.getName() + "\n");
        } 
    }
}