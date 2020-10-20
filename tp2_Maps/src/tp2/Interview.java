package tp2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Interview {

    /** TODO Worst Case : O(n)
     * Is valid if you flip the number upside down.
     */
    public static boolean isValidFlipped(String listOfNumbers) {
        if(listOfNumbers.length() < 2){
            if(listOfNumbers.contains("6") || listOfNumbers.contains("9") || listOfNumbers.contains("4") || listOfNumbers.contains("3") || listOfNumbers.contains("7")){
                return false;
            }
            else{
                return true;
            }

        }

        String [] array = listOfNumbers.split("");
        Integer[] integerArray = new Integer[array.length];

        for (int i = 0; i < array.length; i++) {
            integerArray[i] = Integer.parseInt(array[i]);
        }
        HashMap<Integer, Integer> mapNumbers = new HashMap<Integer, Integer>();
        for ( Integer number : integerArray ) {
            //Integer value = mapNumbers.get(number);
            //if ( value == null ) {
            Integer value;
                if(number == 6){
                    value = 9;
                }
                else if (number == 9){
                    value = 6;
                }
                else if( number == 3 || number == 4 || number == 7){
                    return false;
                }
                else
                {
                  value = number;
                }

           // }

            mapNumbers.put(number, value);
        }

        int offSet = array.length %2 == 0? 0: 1;
        for (int i = 0; i < array.length/2; i++) {
            Integer val = mapNumbers.get(integerArray[array.length-i-1]);
            if(integerArray[i] != val){
                return false;
            }
        }

        return true;
    }

    /** TODO Worst Case : O(n)
     * Could be valid if you try to flip the number upside down with one of the combinations.
     */
    public static boolean isValidFlippedWithPermutation(String listOfNumbers) {
        String [] array = listOfNumbers.split("");
        HashMap<String, Integer> mapNumbers = new HashMap<String, Integer>();


        if(!(listOfNumbers.contains("4") || listOfNumbers.contains("3") || listOfNumbers.contains("7"))){
            if(listOfNumbers.length() == 1 && listOfNumbers.equals("6") || listOfNumbers.equals("9")){
                return false;
            }
            for ( String number : array ) {
                Integer oldCount = mapNumbers.get(number);
                if ( oldCount == null ) {
                    oldCount = 0;

                }

                mapNumbers.put(number, oldCount + 1);
            }
            int cptOdd = 0;
            for(String number : mapNumbers.keySet()){
                if(mapNumbers.get(number) % 2 != 0){
                    cptOdd++;
                }
            }
            Integer cpt6 = mapNumbers.get("6");
            cpt6 = cpt6 == null? 0: cpt6;
            Integer cpt9 = mapNumbers.get("9");
            cpt9 = cpt9 == null? 0: cpt9;

            Integer diff = java.lang.Math.abs(cpt6-cpt9);
            Integer sum = cpt6 + cpt9;
            if(array.length % 2 == 0){

                return cptOdd == 0 && diff == 0 && sum != 1;
            }
            else{
                return cptOdd < 2 && diff < 2;
            }
        }

        return listOfNumbers == "";
    }
}
