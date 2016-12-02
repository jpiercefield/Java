import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.*;

public class CDCollection
{
   private CD[] cds;  //CD must implement java.io.Serializable
   private int size;
   private double totalCost;

   public CDCollection(String file) throws IOException, ClassNotFoundException
   {
      this();
      ArrayList<CD> cds = readCDs(file);  //to use the readSerialized method instead, call the overloaded addAll method
       //      CD[] cds = readSerialCDs(file);
      if (cds != null)
      {
         addAll(cds);  //will compute the totalCost and keep track of the size
      }

   }

   public CDCollection()
   {
      cds = new CD[2];
      size = 0;
      totalCost = 0;
   }

   private static CD[] readSerialCDs(String file) throws IOException, ClassNotFoundException
   {
      CD[] cds = null;

      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream ois = new ObjectInputStream(fis);
      cds = (CD[]) ois.readObject();
      ois.close();

      return cds;
   }

   public void writeSerialCDs(String file) throws IOException 
   {
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(cds);
      oos.close();
   }

   //typically, the read methods will be called from an overloaded constructor
   private static ArrayList<CD> readCDs(String file) throws IOException
   {
      ArrayList<CD> cds = new ArrayList<CD>();

      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);

      String line = br.readLine();

      while(line != null)
      {
         String[] split = line.split(",");
            
         String title;
         String artist;

         try
         {
            title = split[0];
            artist = split[1];
         }
         catch (ArrayIndexOutOfBoundsException aioobe)
         {
            //skip to the next line
            line = br.readLine();
            continue;
         }

         double price = parseDouble(split, 2);  //if a problem occurs, a -1 is returned
         int tracks = parseInt(split, 3);   //the parse methods catch both NFE and AIOOBE

         CD cd = new CD(title, artist, price, tracks);
         cds.add(cd);

         line = br.readLine();
      }
      br.close();

      return cds;
   }

   public void writeCDs(String file) throws IOException
   {
      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);

      for (CD cd : cds)
      {
         try  //could easily use an if statement or a traditional for loop here
         {
            String title = cd.getTitle();
            String artist = cd.getArtist();
            double price = cd.getPrice();
            int tracks = cd.getTracks();
     
            // if you use the CD toString method, you won't get the fields separated by commas, so compose the line manually
            pw.println(title + "," + artist + "," + price + "," + tracks);
         }
         catch (NullPointerException npe)
         {
            break;
         }
      }

      //if your file is always empty, what did you forget?
      pw.close();
   }

   private static int parseInt(String[] str, int index)
   {
      int num = -1;

      try
      {
         num = Integer.parseInt(str[index]);
      }
      catch (NumberFormatException nfe)  //these Exceptions are Runtime, but deal with user input, so we usually catch them
      {
      }
      catch (ArrayIndexOutOfBoundsException aioobe) 
      {
      }

      return num;
   }

   private static double parseDouble(String[] str, int index)
   {
      double num = -1.0;

      try
      {
         num = Double.parseDouble(str[index]);
      }
      catch (NumberFormatException nfe)
      {
      }
      catch (ArrayIndexOutOfBoundsException aioobe)
      {
      }
 
      return num;
   }

   private void addAll(ArrayList<CD> cds)
   {
      for (CD cd : cds)
      {
         add(cd);
      }
   }

   private void addAll(CD[] cds)
   {
      for (int x = 0; x < cds.length; x++)
      {
         if (cds[x] != null)
         {
            add(cds[x]);
         }
      }
   }

   public CD[] copy()
   {
      CD[] temp = new CD[size];
      for (int x = 0; x < size; x++)
      {
         temp[x] = cds[x];
      }
      return temp;
   }

   public void add(CD cd)
   {
      if (size == cds.length)
      {
         increaseSize();
      }

      cds[size] = cd;
      totalCost += cd.getPrice();
      size++;
   }

   private void increaseSize()
   {
      CD[] temp = new CD[2*size];

      for (int x = 0; x < size; x++)
      {
         temp[x] = cds[x];
      }

      cds = temp;
   }

   public String toString()
   {
      DecimalFormat fmt = new DecimalFormat("0.00");

      String temp = "";
      temp += "Number of CDs: " + size + "\r\n";
      temp += "Total Cost: " + "$" + fmt.format(totalCost) + "\r\n";
      double averageCost = 0;
      if (size != 0)
      {
          averageCost = totalCost/size;
      }
      temp += "Average Cost: " + "$" + fmt.format(averageCost) + "\r\n";
      temp += "\r\n";

      //can't use foreach as some locations contain null
      for (int x = 0; x < size; x++)
      {
         temp += cds[x] + "\r\n";
      }

      return temp;
   }

   public void sort()
   {
      Sorting.selectionSort(cds, size);
   }
   
   // methods added Dec. 3, 2006
   public CD getCD(int i)
   {
       if (i >= 0 && i < cds.length)
          return new CD(cds[i].getTitle(), cds[i].getArtist(), cds[i].getPrice(),
                        cds[i].getTracks());
       else
          return null;
   }
   
   public int getSize()
   {
       return size;
   }   
   
   public void obliterate()
   {
       size = 0;
       cds = new CD[2];
       totalCost = 0;
   }
}
