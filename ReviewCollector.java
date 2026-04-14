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
            Pattern n = Pattern.compile("Product:\s*(.*)");
            Pattern r = Pattern.compile("Review:\s*(.*)");
            Matcher m = n.matcher(prodReview.getName());
            Matcher m2 = r.matcher(prodReview.getReview());
            if(m.find() && m2.find()){
                String name = m.group(1);
                String review = m2.group(1);
                ProductReview newReview = new ProductReview(name, review);
                // System.out.println(name);
                // System.out.println(review);
                reviewList.add(newReview);
                productList.add(newReview.getName());
            }
    }
    public double getSentiments(String wordCheck) {
        // Read lines from sentiments.txt
        ArrayList<String> lines = FileOperator.getStringList("sentiments.txt");

        // Regex pattern to match word, decimal pairs
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]+),(-?\\d+\\.\\d+)");
        // Process each line
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                // System.out.println("Line: " + line); // Debugging line to check the content of each line
                String word = matcher.group(1).toLowerCase(); // Extract the word
                Double value = Double.parseDouble(matcher.group(2)); // Extract the value
                // Add to instance variables
                // System.out.println("Extracted word: " + word + ", value: " + value); // Debugging line to check extracted values
                words.add(word);
                values.add(value);
                // System.out.println("Word: " + word + ", Value: " + value + "\n" + wordCheck.toLowerCase()); // Debugging line to check the extracted word and value
                ArrayList<String> wordList = new ArrayList<>();
                for(String wordInReview : wordCheck.toLowerCase().split(" ")){
                    wordList.add(wordInReview);
                }
                for(String wordInReview : wordList){
                if(word.equals(wordInReview))
                {
                    //   System.out.println(word+"     "+value);
                       return value;
                 } //if wordCheck end
            }//if matcher ends
        } //for each line ends
        }
        return 0.0; // Return 0.0 if wordCheck is not found
    }

        public int getNumGoodReviews(String prodname){
        int count = 0;
        for(ProductReview review : reviewList){
            if(review.getName().toLowerCase().equals(prodname.toLowerCase())){
                double total = 0.0;
                for(String word : review.getReview().toLowerCase().split(" ")){
                    for(int i = 0; i < words.size(); i++){
                        String wordToCheck = words.get(i);
                        Double value = values.get(i);
                        if(word.equals(wordToCheck)){
                            total += value;
                        }
                    }
                }
                if(total > 0.0){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        ReviewCollector collector = new ReviewCollector();
        ArrayList<String> reviews = FileOperator.getStringList("product.txt");
        // System.out.println(reviews);
        for(int i = 0; i < reviews.size(); i+=3){
            String line = reviews.get(i);
            String line2 = reviews.get(i+1);
            ProductReview review = new ProductReview(line, line2);
            collector.addReview(review);
        }
        System.out.println(collector.reviewList.size() + " reviews collected.\n");
        for(ProductReview review : collector.reviewList){
            double score = collector.getSentiments(review.getReview().toLowerCase());
            System.out.print(collector.getNumGoodReviews(review.getName()) + " good reviews for " + review.getName() + "\n");
            System.out.print("The sentiment score for " + review.getName() + " is " + score + "\n");
        }
    }
}