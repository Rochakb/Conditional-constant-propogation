import syntaxtree.*;
import visitor.*;

public class P3 {
   public static void main(String [] args) {
      try {
         new TACoJavaParser(System.in);
         Node root = TACoJavaParser.Goal();
         GJDepthFirst2<String,Integer> obj = new GJDepthFirst2<String,Integer>();
         
         root.accept(obj,0); // Your assignment part is invoked here.
         // /root.accept(obj,1); // Your assignment part is invoked here.
         root.accept(obj,2); // Your assignment part is invoked here.
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 



