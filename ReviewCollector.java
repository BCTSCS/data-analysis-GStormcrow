import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewCollector{
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<Double> values = new ArrayList<>();
    
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
                if(review.getReview().toLowerCase().contains("best")){
                    count++;
                }
            }
        }
        return count;
    }
        public double getReviewSentiment(String prodname){
        double score = 0.0;
        for(ProductReview review : reviewList){
            if(review.getName().equals(prodname)){
                for(String word : review.getReview().toLowerCase().split(" ")){
                    for(int i = 0; i < words.size(); i++){
                        String wordToCheck = words.get(i);
                        Double value = values.get(i);
                        if(word.equals(wordToCheck)){
                            score += value;
                        }
                    }
                }
            }
        }
        return score;
    }
    
      public void processSentiments(String filePath) {
        // Read lines from sentiments.txt
        ArrayList<String> lines = FileOperator.getStringList(filePath);

        // Regex pattern to match word,decimal pairs
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]+),(-?\\d+\\.\\d+)");


        // Process each line
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String word = matcher.group(1); // Extract the word
                Double value = Double.parseDouble(matcher.group(2)); // Extract the value

                // Add to instance variables
                words.add(word);
                values.add(value);

                // Print the result
                // System.out.println(word + "   ----  " + value);
   
            }
        }
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
        collector.processSentiments("sentiments.txt");
        for(ProductReview review : collector.reviewList){
            System.out.print("The sentiment score for " + review.getName() + " is " + collector.getReviewSentiment(review.getName()) + "\n");
        }
    }
}