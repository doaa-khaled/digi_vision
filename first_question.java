import java.util.*;
import java.util.regex.*;
import org.apache.commons.lang.StringUtils;

public class Main {
    
    public static void main(String[] args) {
      System.out.println("Hello, World!");
      reverseSubstring("sdsss");
      reverseSubstring("1111");
      reverseSubstring("SAS");
      reverseSubstring("sdsss(");
      reverseSubstring("sdsss(avv)mmm(dhdee)");
      reverseSubstring("");

    }
  
  
    public static String reverseSubstring (String input){
      try{
          if(input.length() < 1 || input.length() > 2000) {
            throw new Exception("Length of passed string should be between 1 & 2000!");
          }
          String temp = input.replaceAll("\\(", "").replaceAll("\\)", "");
          if(!temp.matches("[a-z]+")) {
            throw new Exception("String should be lowercase english letters only!");
          }
          if(!input.contains("(") || (StringUtils.countMatches(input, "(") != StringUtils.countMatches(input, ")"))) {
            throw new Exception("String should contains balanced parentheses!");
          }  
          Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(input);
          StringBuilder substring = null;
          while(m.find()) {
            substring = new StringBuilder(m.group(1)).reverse();
            input = (input.replace("(" + m.group(1) + ")","(" + substring + ")"));
          }
          System.out.println(input);

      }catch(Exception e){
        System.out.println(e.getMessage());
      }
      return input;
    }
}
