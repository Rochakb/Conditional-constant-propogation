//
// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;
 //
// Structure to build the CFG.  #code
//


/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJDepthFirst3<R,A> implements GJVisitor<R,A> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   

   StringBuffer decl =new StringBuffer();
   StringBuffer sb = new StringBuffer();
   StringBuffer temp = new StringBuffer();
   
  
   HashMap<Node,HashMap> snap_map = new HashMap<Node,HashMap>();
   HashMap<String,HashMap> sc_map = new HashMap<String,HashMap>();	
   String constant = "default";	
   boolean flag = false;	
   String current = "default";

  
   public void print(HashMap<String, String> hm) {	
      Iterator itr = hm.entrySet().iterator();	
      while (itr.hasNext()) {	
         Map.Entry map = (Map.Entry)itr.next();	
         System.out.print(map.getKey() + " : " + map.getValue()+", ");	
      }	
   }

   public void print_all() {	
      Iterator itr1 = sc_map.entrySet().iterator();
      while(itr1.hasNext())
      {
         Map.Entry map1 = (Map.Entry)itr1.next();	
         System.out.println(map1.getKey());
         HashMap<String, String> hm = sc_map.get(map1.getKey());

         Iterator itr2 = hm.entrySet().iterator();	
          while (itr2.hasNext()) {	
            Map.Entry map2 = (Map.Entry)itr2.next();	
            System.out.print(map2.getKey() + " : " + map2.getValue()+", ");	
         }	
      }
   }

    public void print_a(HashMap hmap){
        if(hmap!=null){
            Iterator itr = hmap.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry map2 = (Map.Entry)itr.next();
                System.out.print(map2.getKey() + " : " + map2.getValue()+", ");
                return;
            }
        }
        System.out.println("------------NULL---------");
    }

   public String search(String var) 
   {       
      HashMap<String, String> hm = sc_map.get(current);
      if(hm.containsKey(var))
         return current;

      String str = current;     
      String[] arrOfStr = str.split(" ", 0); 
      int len = arrOfStr.length;
      for(int i=len-1; i>=0; --i){
         hm = sc_map.get(arrOfStr[i]);
         if(hm.containsKey(var))
            return arrOfStr[i];
      }
      return "";
   } 
   public String searchValue(String var) {	    
      HashMap<String, String> hmap = sc_map.get(current);

      //System.out.println(current+"**********"+var);	
        // print_a(sc_map);	
        //print_all();	
        //System.out.println(hmap.containsKey(current));	
        //System.out.println(hmap.getKeys()+"********");	
        String s = search(var);	
        //System.out.println(s);	
        hmap = sc_map.get(s);	
        String x = hmap.get(var);	
        sb.append("\t\t"+var +" : "+x+"\t\t");	
        if(x!="T"&&x!="B")	
            return x;	
        else
            return var;
      
   }

    public String searchValue2(String var,Node n) {	   
        HashMap<String, HashMap> sc_cpy = snap_map.get(n);
        System.out.print("got snap\t");
        HashMap<String, String> hmap = sc_cpy.get(current);
        System.out.print("got hmap\t");
        String s = search(var);	
        System.out.print("got location\t");
        hmap = sc_cpy.get(s);	
        String x = hmap.get(var);	
        System.out.println("var: "+var+ "   value found: "+x);
        sb.append("\t\t"+var +" : "+x+"\t\t");	
        if(x!="T"&&x!="B")	
            return x;	
        else
            return var;

    }

      
   public R visit(NodeList n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n, A argu) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n, A argu) {
      if ( n.present() )
         return n.node.accept(this,argu);
      else
         return null;
   }

   public R visit(NodeSequence n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   //public R visit(NodeToken n, A argu) { return null; }
   String token = "ashjdhaskd";
   public R visit(NodeToken n, A argu) { 
      token = n.tokenImage;
      return null; 
   }
   

   //
   // User-generated visitor methods below
   //


   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
   public R visit(Goal n, A argu) {
      R _ret=null;
      sb.delete(0, sb.length());

      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      System.out.print("\n\n"+sb+"\n");
      print_all();
      //System.out.println("");
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> "public"
    * f4 -> "static"
    * f5 -> "void" 
    * f6 -> "main"
    * f7 -> "("
    * f8 -> "String"
    * f9 -> "["
    * f10 -> "]"
    * f11 -> Identifier()
    * f12 -> ")"
    * f13 -> "{"
    * f14 -> ( VarDeclaration() )*
    * f15 -> ( Statement() )*
    * f16 -> "}"
    * f17 -> "}"
    */
   public R visit(MainClass n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        n.f1.accept(this, argu);	          //identifer
        String class_name = token;	
        HashMap<String,String> hmap = new HashMap<String,String>();	
        sc_map.put(class_name,hmap);	
        current = class_name;	            //update current
        
        n.f14.accept(this, argu);	
        n.f15.accept(this, argu);
      }
      else if((Integer)argu==1){

      }
        else{ 	System.out.println("-------------------------");
                print_all();
                System.out.println("\n-------------------------");
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         String var = token;
         current = var;
         n.f2.accept(this, argu);
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
         n.f5.accept(this, argu);
         n.f6.accept(this, argu);
         n.f7.accept(this, argu);
         n.f8.accept(this, argu);
         n.f9.accept(this, argu);
         n.f10.accept(this, argu);
         n.f11.accept(this, argu);
         String var2 = token;
         n.f12.accept(this, argu);
         n.f13.accept(this, argu);
         sb.append("GJDepthFirst2\nclass "+ var +"{\npublic static void main(String[] "+ var2 +"){");                    //appending
         //add code
         n.f14.accept(this, argu);     
         n.f15.accept(this, argu);
         sb.append("}\n}\n");                                           //appending
         n.f16.accept(this, argu);
         n.f17.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
   public R visit(TypeDeclaration n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> ( VarDeclaration() )*
    * f4 -> ( MethodDeclaration() )*
    * f5 -> "}"
    */
   public R visit(ClassDeclaration n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        n.f1.accept(this, argu);	          //identifer
        String class_name = token;	
        HashMap<String,String> hmap = new HashMap<String,String>();	
        sc_map.put(class_name,hmap);	
        current = class_name;	            //update current
        n.f3.accept(this, argu);	
        n.f4.accept(this, argu);	
      }
      else if((Integer)argu==1){
         
      }
      else{ 
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         String var = token;
         current = var;
         sb.append("class "+var+"{\n\t");              //append
         n.f2.accept(this, argu);
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
         n.f5.accept(this, argu);
         sb.append("}\n");                             //append
      } 
      return _ret;

   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "extends"
    * f3 -> Identifier()
    * f4 -> "{"
    * f5 -> ( VarDeclaration() )*
    * f6 -> ( MethodDeclaration() )*
    * f7 -> "}"
    */
   public R visit(ClassExtendsDeclaration n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        n.f1.accept(this, argu);	          //identifer
        String class_name = token;	
        HashMap<String,String> hmap = new HashMap<String,String>();	
        sc_map.put(class_name,hmap);	
        current = class_name;	            //update current
        n.f5.accept(this, argu);	
        n.f6.accept(this, argu);	
      }
      else if((Integer)argu==1){
         
      }
      else{ 
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         String var = token;
         current = var;
         n.f2.accept(this, argu);
         n.f3.accept(this, argu);
         String var2 = token;
         current = var;
         n.f4.accept(this, argu);
         sb.append("class "+var+"extends "+var2+"{");            //appending 
         n.f5.accept(this, argu);
         n.f6.accept(this, argu);
         current = var;
         n.f7.accept(this, argu);
         sb.append("}");
         System.out.print("\n\n"+sb+"\n\n");
      }
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> ";"
    */
   public R visit(VarDeclaration n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        HashMap hm = sc_map.get(current);	
        n.f0.accept(this, argu);	
        n.f1.accept(this, argu);	
        String id_name = token;
        hm.put(id_name,"T");	
        snap_map.put(n, (HashMap)sc_map.clone());
        //System.out.println(current+" , "+id_name+" "+hm.get(id_name));
      }
      else if((Integer)argu==1){
         
      }
      else{ 
         n.f0.accept(this, argu);
         String type = token;
         n.f1.accept(this, argu);
         String var = token;
         if(type=="]")
            type="int[]";
         sb.append(type+" "+ var + ";\n");                     //appending
         n.f2.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> "public"
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( FormalParameterList() )?
    * f5 -> ")"
    * f6 -> "{"
    * f7 -> ( VarDeclaration() )*
    * f8 -> ( Statement() )*
    * f9 -> "return"
    * f10 -> Identifier()
    * f11 -> ";"
    * f12 -> "}"
    */
   public R visit(MethodDeclaration n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
         HashMap<String,String> hmap = new HashMap<String,String>();	
         n.f2.accept(this, argu);	         //identifier
         String id_name = token;	
         String method_name = current +" "+ id_name;	 //method name
         sc_map.put(method_name,hmap);	
         current = method_name;             //update current new method name
         
         
         n.f7.accept(this, argu);
         n.f8.accept(this, argu);
         n.f10.accept(this, argu);
         current= argu.toString();          //update current back to previous one
      }
      else if((Integer)argu==1){
         
      }
      else{
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         String type = token; 
         n.f2.accept(this, argu);
         String var = token; 
         current = current + " " + var;
         n.f3.accept(this, argu);
         sb.append("public "+ type + " " + var +"( ");            //appending
         n.f4.accept(this, argu);
         sb.append("){");                                         //appending
         n.f5.accept(this, argu);
         n.f6.accept(this, argu);
         n.f7.accept(this, argu);
         n.f8.accept(this, argu);
         n.f9.accept(this, argu);
         n.f10.accept(this,argu);
         String var2 = token; 
         sb.append("return "+var2+";\n}");                           //appending
         n.f11.accept(this, argu);
         n.f12.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
   public R visit(FormalParameterList n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public R visit(FormalParameter n, A argu) {

      R _ret=null;
      n.f0.accept(this, argu);
      String type = token;
      n.f1.accept(this, argu);
      String var = token;
      sb.append(type + " "+ var);
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> FormalParameter()
    */
   public R visit(FormalParameterRest n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      sb.append(" , ");
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> ArrayType()
    *       | BooleanType()
    *       | IntegerType()
    *       | Identifier()
    */
   public R visit(Type n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "int"
    * f1 -> "["
    * f2 -> "]"
    */
   public R visit(ArrayType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "boolean"
    */
   public R visit(BooleanType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "int"
    */
   public R visit(IntegerType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Block()
    *       | AssignmentStatement()
    *       | ArrayAssignmentStatement()
    *       | FieldAssignmentStatement()
    *       | IfStatement()
    *       | WhileStatement()
    *       | ForStatement()
    *       | PrintStatement()
    */
   public R visit(Statement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "{"
    * f1 -> ( Statement() )*
    * f2 -> "}"
    */
   public R visit(Block n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
         n.f1.accept(this, argu);
      }
      else if((Integer)argu==1){
         
      }
      else{ 
         sb.append("\n{\n");
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         sb.append("\n}\n");
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
   public R visit(AssignmentStatement n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());
       
         n.f2.accept(this, argu);	       // CHECK FOR CONSTANT
         n.f0.accept(this, argu);	       // FINDS THE TOKEN
         if(flag == true)	                // IF CONSTANT
         {
            //System.out.println("\nyes constant");n
            String map_key = search(token);
            //System.out.println("key "+map_key+":"+token);
            HashMap<String,String> hmap = sc_map.get(map_key);	         //get hash map
            String val = hmap.get(token);
            //System.out.println("value before "+hmap.get(token));
            if(val=="T")
               hmap.replace(token,constant);	
            else if(val!="B" && val!=constant)	
               hmap.replace(token, val, "B");
            //System.out.println("value update "+hmap.get(token));
            print_all();
            flag=false;
         }
         else{
            //System.out.println("No constant");  
         }
         
      }
      else if((Integer)argu==1){
         
      }
      else{ 
         n.f0.accept(this, argu);
         String var = token;
         sb.append(""+var+" = ");
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         sb.append(";\n");
         n.f3.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Identifier()
    * f3 -> "]"
    * f4 -> "="
    * f5 -> Identifier()
    * f6 -> ";"
    */
   public R visit(ArrayAssignmentStatement n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());
      }
      else if((Integer)argu==1){
   
      }
      else{ 
         n.f0.accept(this, argu);
         String var = token;
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
         String var3 = token;
         sb.append(var+"["+var2+"]"+" = "+var3+"; ");
         n.f5.accept(this, argu);
         n.f6.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "="
    * f4 -> Identifier()
    * f5 -> ";"
    */
   public R visit(FieldAssignmentStatement n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());
      }
      else if((Integer)argu==1){
         
      }else{
         n.f0.accept(this, argu);
         String var = token;
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
         String var3 = token;
         sb.append(var+"."+var2+" = "+var3+";\n");
         n.f5.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Identifier()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> "else"
    * f6 -> Statement()
    */
   public R visit(IfStatement n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());
         //System.out.println("if start");
         n.f4.accept(this, argu);
         n.f6.accept(this, argu);
            //take a flag for if   // keep s1 s2 snaps if flag true merge s1 s2
      }
      else if((Integer)argu==1){
         
      }else{
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var = token;
         sb.append("if("+var+")\n\t");
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
         n.f5.accept(this, argu);
         sb.append("else\n\t");
         n.f6.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Identifier()
    * f3 -> ")"
    * f4 -> Statement()
    */
   public R visit(WhileStatement n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());
        n.f4.accept(this, argu);
      }
      else if((Integer)argu==1){
         
      }else{
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var  = token;
         sb.append("while("+var+")\n\t");
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> "for"
    * f1 -> "("
    * f2 -> Identifier()
    * f3 -> "="
    * f4 -> Expression()
    * f5 -> ";"
    * f6 -> Expression()
    * f7 -> ";"
    * f8 -> Identifier()
    * f9 -> "="
    * f10 -> Expression()
    * f11 -> ")"
    * f12 -> Statement()
    */
   public R visit(ForStatement n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());
        n.f12.accept(this, argu);
      }
      else if((Integer)argu==1){
         
      }else{
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var = token;
         sb.append("for("+var+"=");
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
         sb.append(";");
         n.f5.accept(this, argu);
         n.f6.accept(this, argu);
         n.f7.accept(this, argu);
         sb.append(";");
         n.f8.accept(this, argu);
         String var2 = token;
         sb.append(var+"=");
         n.f9.accept(this, argu);
         n.f10.accept(this, argu);
         sb.append(")\n\t");
         n.f11.accept(this, argu);
         n.f12.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Identifier()
    * f3 -> ")"
    * f4 -> ";"
    */
   public R visit(PrintStatement n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());   //take snap before sopln, no need to call n.f0
      }
      else if((Integer)argu==1){
         
      }else{
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var = token;
         var = searchValue2(var,n);
         sb.append("\nSystem.out.println("+var+");\n");
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> AndExpression()
    *       | CompareExpression()
    *       | PlusExpression()
    *       | MinusExpression()
    *       | TimesExpression()
    *       | ArrayLookup()
    *       | ArrayLength()
    *       | MessageSend()
    *       | PrimaryExpression()
    */
   public R visit(Expression n, A argu) {
      R _ret=null;
      flag=false;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "&"
    * f2 -> Identifier()
    */
   public R visit(AndExpression n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());   
        HashMap<String, String> hmap;
        n.f0.accept(this, argu);
        String var = token;
        String s1 = search(var);
        hmap = sc_map.get(s1);
        String x = hmap.get(var);

        n.f2.accept(this, argu);
        String var2 = token;
        String s2 = search(var);
        hmap = sc_map.get(s1);  
        String y = hmap.get(var2);
        if(x!="T"&&x!="B"&&y!="T"&&y!="B")
           if(Integer.parseInt(x)<Integer.parseInt(y)){
              constant = "true";
              flag = true;
           }else{
              constant = "false";
              flag = true;
           }
     }
      if((Integer)argu==2){
            n.f0.accept(this, argu);
            String var = token;
            var = searchValue2(var,n);
            n.f1.accept(this, argu);
            n.f2.accept(this, argu);
            String var2 = token;
            var2 = searchValue2(var2,n);
            sb.append(var+" & "+var2);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "<"
    * f2 -> Identifier()
    */
   public R visit(CompareExpression n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());   
         HashMap<String, String> hmap;
         n.f0.accept(this, argu);
         String var = token;
         String s1 = search(var);
         hmap = sc_map.get(s1);
         String x = hmap.get(var);

         n.f2.accept(this, argu);
         String var2 = token;
         String s2 = search(var);
         hmap = sc_map.get(s1);  
         String y = hmap.get(var2);
         if(x!="T"&&x!="B"&&y!="T"&&y!="B")
            if(Integer.parseInt(x)<Integer.parseInt(y)){
               constant = "true";
               flag = true;
            }else{
               constant = "false";
               flag = true;
            }
      }
      else if((Integer)argu==1){

      }
      else if((Integer)argu==2){
         n.f0.accept(this, argu);
         String var = token;
         var = searchValue2(var,n);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         var2 = searchValue2(var2,n);
         sb.append(var+" < "+var2);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "+"
    * f2 -> Identifier()
    */
   public R visit(PlusExpression n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());   
         HashMap<String, String> hmap ;
         n.f0.accept(this, argu);
         String var = token;
         String s1 = search(var);
         hmap = sc_map.get(s1);
         String x = hmap.get(var);

         n.f2.accept(this, argu);
         String var2 = token;
         String s2 = search(var);
         hmap = sc_map.get(s1);
         String y = hmap.get(var2);
         if(x!="T"&&x!="B"&&y!="T"&&y!="B")
         {
            constant = String.valueOf(Integer.parseInt(x) + Integer.parseInt(y));
            flag =true;
         }
      }
      else if((Integer)argu==1){

      }
      else if((Integer)argu==2){
         n.f0.accept(this, argu);
         String var = token;
         var = searchValue2(var,n);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         var2 = searchValue2(var2,n);
         sb.append(var+" + "+var2);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "-"
    * f2 -> Identifier()
    */
   public R visit(MinusExpression n, A argu) {
      R _ret=null;if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());   
         HashMap<String, String> hmap ;
         n.f0.accept(this, argu);
         String var = token;
         String s1 = search(var);
         hmap = sc_map.get(s1);
         String x = hmap.get(var);

         n.f2.accept(this, argu);
         String var2 = token;
         String s2 = search(var);
         hmap = sc_map.get(s1);
         String y = hmap.get(var2);
         if(x!="T"&&x!="B"&&y!="T"&&y!="B")
         {
            constant = String.valueOf(Integer.parseInt(x) - Integer.parseInt(y));
            flag =true;
         }
      }
      else if((Integer)argu==1){

      }
      else if((Integer)argu==2){
         n.f0.accept(this, argu);
         String var = token;
         var = searchValue2(var,n);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         var2 = searchValue2(var2,n);
         sb.append(var+" - "+var2);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "*"
    * f2 -> Identifier()
    */
   public R visit(TimesExpression n, A argu) {
      R _ret=null;
      if((Integer)argu==0){
        snap_map.put(n, (HashMap)sc_map.clone());   
         HashMap<String, String> hmap ;
         n.f0.accept(this, argu);
         String var = token;
         String s1 = search(var);
         hmap = sc_map.get(s1);
         String x = hmap.get(var);

         n.f2.accept(this, argu);
         String var2 = token;
         String s2 = search(var);
         hmap = sc_map.get(s1);
         String y = hmap.get(var2);
         if(x!="T"&&x!="B"&&y!="T"&&y!="B")
         {
            constant = String.valueOf(Integer.parseInt(x) * Integer.parseInt(y));
            flag =true;
         }
      }
      else if((Integer)argu==1){

      }
      else if((Integer)argu==2){
         n.f0.accept(this, argu);
         String var = token;
         var = searchValue2(var,n);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         var2 = searchValue2(var2,n);
         sb.append(var+" * "+var2);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Identifier()
    * f3 -> "]"
    */
   public R visit(ArrayLookup n, A argu) {
      R _ret=null;
      if((Integer)argu==0){	
            snap_map.put(n, (HashMap)sc_map.clone());	
        }
      if((Integer)argu==2){
         n.f0.accept(this, argu);
         String var = token;
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         var2 = searchValue2(var2,n);
         sb.append(var+"["+var2+"]");
         n.f3.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "."
    * f2 -> "length"
    */
   public R visit(ArrayLength n, A argu) {
      R _ret=null;
      if((Integer)argu==0){	
            snap_map.put(n, (HashMap)sc_map.clone());	
        }
      if((Integer)argu==2){
         n.f0.accept(this, argu);
         String var = token;
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         sb.append(var+"."+var2);
      }
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( ArgList() )?
    * f5 -> ")"
    */
   public R visit(MessageSend n, A argu) {
      R _ret=null;
      if((Integer)argu==0){	
            snap_map.put(n, (HashMap)sc_map.clone());	
        }
      if((Integer)argu==2){
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         String var2 = token;
         sb.append("."+var2+"(");
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
         n.f5.accept(this, argu);
         sb.append(")");
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> ( ArgRest() )*
    */
   public R visit(ArgList n, A argu) {
      R _ret=null;
      if((Integer)argu==0){	
            snap_map.put(n, (HashMap)sc_map.clone());	
        }
      if((Integer)argu==2){
         n.f0.accept(this, argu);
         String var = token;
         var = searchValue2(var,n);
         sb.append(var+"");
         n.f1.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> Identifier()
    */
   public R visit(ArgRest n, A argu) {
      R _ret=null;
      if((Integer)argu==0){	
            snap_map.put(n, (HashMap)sc_map.clone());	
        }
      if((Integer)argu==2){
         n.f0.accept(this, argu);
         sb.append(",");
         n.f1.accept(this, argu);
         String var = token;
         var = searchValue2(var,n);
         sb.append(var+"");
      }
      return _ret;
   }

   /**
    * f0 -> IntegerLiteral()
    *       | TrueLiteral()
    *       | FalseLiteral()
    *       | Identifier()
    *       | ThisExpression()
    *       | ArrayAllocationExpression()
    *       | AllocationExpression()
    *       | NotExpression()
    */
   public R visit(PrimaryExpression n, A argu) {
      R _ret=null;	
      n.f0.accept(this, argu);	
      if((Integer)argu==0){	
         if(n.f0.which == 0){	
            flag = true;	
            constant = token.toString();	
         }	
      }	
      else if((Integer)argu==2){	
         if(n.f0.which == 3){	
            String var = token;
            var = searchValue2(var,n);
            sb.append(var);	
         }
      }
      return _ret;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      if((Integer)argu==2){
         sb.append(token);
      }
      return _ret;
   }

   /**
    * f0 -> "true"
    */
   public R visit(TrueLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      if((Integer)argu==2){
         sb.append("true");
      }
      return _ret;
   }

   /**
    * f0 -> "false"
    */
   public R visit(FalseLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      if((Integer)argu==2){
         sb.append("false");
      }
      return _ret;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public R visit(Identifier n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      if((Integer)argu==2){
         //sb.append(token);
      }
      return _ret;
   }

   /**
    * f0 -> "this"
    */
   public R visit(ThisExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      if((Integer)argu==2){
         sb.append("this");
      }
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Identifier()
    * f4 -> "]"
    */
   public R visit(ArrayAllocationExpression n, A argu) {
      R _ret=null;
      if((Integer)argu==0){	
            snap_map.put(n, (HashMap)sc_map.clone());	
        }
      if((Integer)argu==2){
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         n.f3.accept(this, argu);
         String var = token;
         var = searchValue2(var,n);
         n.f4.accept(this, argu);
         sb.append("new int ["+var+"]");
      }
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
   public R visit(AllocationExpression n, A argu) {
      R _ret=null;
      if((Integer)argu==0){	
            snap_map.put(n, (HashMap)sc_map.clone());	
        }
      if((Integer)argu==2){
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         String var = token;
         sb.append("new "+var+"()");
         n.f2.accept(this, argu);
         n.f3.accept(this, argu);
      }
      return _ret;
   }

   /**
    * f0 -> "!"
    * f1 -> Identifier()
    */
   public R visit(NotExpression n, A argu) {
      R _ret=null;
      if((Integer)argu==0){	
            snap_map.put(n, (HashMap)sc_map.clone());	
        }
      if((Integer)argu==2){
         n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         String var = token;
         sb.append("!"+var);
      }
      return _ret;
   }

}
