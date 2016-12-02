public class RatingException extends Exception
{
   public RatingException()
   {
      this("General Rating problem");
   }

   public RatingException(String s)
   {
      super(s);
   }
}
