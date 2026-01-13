import java.util.ArrayList;
public class Re {
    public static void main(String[] args) {
        // String re = "[AA|BAAB]+";
        String re = "AB*A";
        // String text = "BAABBAAB";
        String text = "ABBBBA";
        ArrayList<String> posts = FileOperator.getStringList("posts.txt");
        System.out.println(posts);
        for(String post: posts){
            Boolean result = post.matches(re);
            System.out.println(post + ": " + result);
        }
        Boolean result = text.matches(re);
        System.out.println(result);
    }
}