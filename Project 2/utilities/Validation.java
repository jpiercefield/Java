package utilities;
public class Validation {  
   public static boolean isNameInvalid(String s)
   {
      return s == null || s.equals("");
   }
   
   public static boolean isNonnumeric(String s)
   {
      for (char ch : s.toCharArray())
      {
          if (!Character.isDigit(ch))
          {
             return true;
          }
      }
      return false;
   }
   
   public static boolean isDateInvalid(String date) {
       return false;
   }
}