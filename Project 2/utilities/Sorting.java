/**
 *  Utility class to provide sorting algorithms.
 */
package utilities;
public class Sorting
{


   /**
    * Sorts the specified array of Comparable objects using the selection
    * sort algorithm. <br>
    * The entire array is sorted.<br>  
    */
   public static void selectionSort(Comparable[] sort)
   {
      if (sort != null)
      {
         selectionSort(sort, sort.length);
      }
   }

   /**
    * Sorts the specified array of Comparable objects using the selection
    * sort algorithm. <br>  
    * The number of items sorted (starting at the beginning of the array) is n.
    */
   public static void selectionSort (Comparable[] sort, int n)
   {
      //should check n to make sure that it doesn't exceed the array parameters
      int min;
      Comparable temp;

      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }

      for (int index = 0; index < n - 1; index++)
      {
         min = index;
         for (int scan = index+1; scan < n; scan++)
         {
            if (sort[scan].compareTo(sort[min]) < 0)
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


   /**
    * Sorts the specified array of Comparable objects using the insertion
    * sort algorithm. <br>
    * The entire array is sorted.<br>  
    */
   public static void insertionSort(Comparable[] sort)
   {
      if (sort != null)
      {
         insertionSort(sort, sort.length);
      }
   }

   /**
    * Sorts the specified array of Comparable objects using the insertion
    * sort algorithm. <br>  
    * The number of items sorted (starting at the beginning of the array) is n.
    */
   public static void insertionSort (Comparable[] sort, int n)
   {
      Comparable temp;
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
         while (position > 0 && sort[position-1].compareTo(temp) > 0)
         {
            sort[position] = sort[position-1];
            position--;
         }
            
         sort[position] = temp;
      }
   }


}