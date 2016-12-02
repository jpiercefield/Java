package utilities;
import java.util.Comparator;
public class Sorting
{
   public static <E> void selectionSort(E[] sort, Comparator<E> comparator)
   {
       if (sort != null && sort.length > 0)
       {
           selectionSort(sort, sort.length, comparator);
       }
   }
   
   public static <E> void selectionSort(E[] sort, int n, Comparator<E> comparator)
   {
      //should check n to make sure that it doesn't exceed the array parameters
      int min;
      E temp;

      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }

      for (int index = 0; index < n - 1; index++)
      {
         min = index;
         for (int scan = index+1; scan < n; scan++)
         {
            if (comparator.compare(sort[scan], sort[min]) < 0)
            {
                min = scan;
            }
         }

         // Swap the values
         temp = sort[min];
         sort[min] = sort[index];
         sort[index] = temp;
      }
   }

   public static <E> void insertionSort(E[] sort, Comparator<E> comparator)
   {
       if (sort != null && sort.length > 0)
       {
           insertionSort(sort, sort.length, comparator);
       }
   }

   public static <E> void insertionSort(E[] sort, int n, Comparator<E> comparator)
   {
      E temp;
      int position;

      if (n>sort.length || n<=0)
      {
         n=sort.length;
      }

      for (int index = 1; index < n; index++)
      {
         temp = sort[index];
         position = index;

         // shift larger values to the right
         while (position > 0 && comparator.compare(sort[position-1], temp) > 0)
         {
            sort[position] = sort[position-1];
            position--;
         }
            
         sort[position] = temp;
      }
   }
   
    public static <E> void bubbleSort(E[] sort, Comparator<E> comparator)
   {
       if (sort != null && sort.length > 0)
       {
           bubbleSort(sort, sort.length, comparator);
       }
   }
   
   public static <E> void bubbleSort(E[] sort, int n, Comparator<E> comparator)
   {
      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }
/*
      boolean sorted = false;  // false when swaps occur
      for (int pass = 1; (pass < n) && !sorted; pass++) 
      {
         sorted = true;  // assume sorted
         for (int index = 0; index < n - pass; index++) 
         {
            if (comparator.compare(sort[index], sort[index + 1]) > 0) 
            {
               // exchange items
               E temp = sort[index];
               sort[index] = sort[index + 1];
               sort[index + 1] = temp;
               sorted = false;  // signal that an exchange occured
            }  
         }  

      } 
*/

      for (int index = 0; index < (n - 1); index++) 
      {
         for (int scan = 0; scan < (n - index - 1); scan++) 
         {
            if (comparator.compare(sort[scan], sort[scan + 1]) > 0) 
            {
               // exchange items
               E temp = sort[scan];
               sort[scan] = sort[scan + 1];
               sort[scan + 1] = temp;
            }  
         }  

      } 
   }   
}