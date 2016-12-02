
public class CDCollectionDriver
{
   public static void main(String[] args)
   {

      try
      {
         //        CDCollection cds = new CDCollection("cds.dat");
         CDCollection cds = new CDCollection("cds.txt");
         System.out.println(cds);
         cds.sort();
         System.out.println(cds);

         cds.writeSerialCDs("cds.dat");
         cds.writeCDs("cds.txt");
      } 
      catch (ClassNotFoundException cnfe)
      {
         System.out.println("Could not read from the file.");
         System.out.println(cnfe.getMessage());
      }
      catch (java.io.IOException ioe)
      {
         System.out.println("Could not read/write from/to the file.");
         System.out.println(ioe.getMessage());
      }

   }

}