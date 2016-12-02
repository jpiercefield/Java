public class RestaurantException extends Exception
{
   public RestaurantException()
   {
      this("General Restaurant problem");
   }

   public RestaurantException(String s)
   {
      super(s);
   }
}
