package utilities;
public class Validation {  
   
    public static boolean isDateInvalid(String date) {
        // date must be in yy/mm/dd format
        boolean result = true;
        int month = 0;
        int day = 0;
        int year = 0;
        try {
            if (date.length() == 10) {
               String[] pieces = date.split("/");
               if (pieces.length == 3 && pieces[0].length() == 2 && pieces[1].length() == 2 && pieces[2].length() == 4) {
                   month = Integer.parseInt(pieces[0]);
                   day = Integer.parseInt(pieces[1]);
                   year = Integer.parseInt(pieces[2]);
               }
               if (year >= 0 && year <= 9999)
               {
                   if (month >= 1 && month <= 12 && day >= 1 && day <= 28 && year >= 0 && year <= 9999) {
                       result = false;
                    }
                    if ((day == 29 || day == 30) && (month == 4 || month == 6 || month == 9 || month == 11) && year >= 0 && year <= 9999) {
                        result = false;
                    }
                    if (day == 29 && month == 2 && year >= 0 && year <= 9999 && year % 4 == 0) { // leap year
                        result = false;
                    }
                    if (day >= 29 && day <= 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||
                                                   month == 10 || month == 12)) {
                       result = false;
                    }
               }
            }
        }
        catch (NumberFormatException nfe) {
        }
        return result;
    }
}