public class MenuItemException extends Exception
{
   public MenuItemException()
   {
      this("General Student Success Center problem");
   }

   public MenuItemException(String s)
   {
      super(s);
   }
}