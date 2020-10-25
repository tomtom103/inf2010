package tp2;

import java.util.HashMap;


public final class Interview {

    /** TODO Worst Case : O(n)
     * Is valid if you flip the number upside down.
     */
    public static boolean isValidFlipped(String listOfNumbers) {

        int stringLength = listOfNumbers.length();
        if(stringLength < 2){
            return !listOfNumbers.equals("6") && !listOfNumbers.equals("9") && !listOfNumbers.equals("3") && !listOfNumbers.equals("4") && !listOfNumbers.equals("7");
        }
        HashMap<Character, Character> mapNumbers = new HashMap<>();
        // 6 is 9 flipped and vice versa
        mapNumbers.put('6','9');
        mapNumbers.put('9','6');

        for (int i = 0; i < stringLength/2; i++) {
            Character base = listOfNumbers.charAt(i);
            if(base.equals('3') || base.equals('4') || base.equals('7')){
                return false;
            }
            else if(base.equals('6') || base.equals('9')){
                Character val = mapNumbers.get(listOfNumbers.charAt(stringLength-i-1));
                if(base != val){
                    return false;
                }
            }
            else if(!base.equals(listOfNumbers.charAt(stringLength-i-1))){
                return false;
            }
        }
        return true;
    }

    /** TODO Worst Case : O(n)
     * Could be valid if you try to flip the number upside down with one of the combinations.
     */
    public static boolean isValidFlippedWithPermutation(String listOfNumbers) {

        HashMap<Character, Integer> mapNumbers = new HashMap<>();
        int listLength = listOfNumbers.length();

        if((listOfNumbers.equals("6") || listOfNumbers.equals("9"))){
            return false;
        }
        for (int i = 0; i < listLength; i++) {
            Character value = listOfNumbers.charAt(i);
            if(value.equals('3')||value.equals('7')||value.equals('4')){
                return false;
            }
            Integer oldCount = mapNumbers.get(value);
            if ( oldCount == null ) {
                oldCount = 0;
            }
            mapNumbers.put(value, oldCount + 1);
        }
        int cptOdd = 0;
        for(Character number : mapNumbers.keySet()){
            if(mapNumbers.get(number) % 2 != 0){
                cptOdd++;
            }
        }
        Integer cpt6 = mapNumbers.get('6');
        cpt6 = cpt6 == null? 0: cpt6;
        Integer cpt9 = mapNumbers.get('9');
        cpt9 = cpt9 == null? 0: cpt9;

        int diff = java.lang.Math.abs(cpt6-cpt9);
        int sum = cpt6 + cpt9;
        if(listLength % 2 == 0){
            return cptOdd == 0 && diff == 0 && sum != 1;
        }
        else{
            return cptOdd < 2 && diff < 2;
        }
    }
}
