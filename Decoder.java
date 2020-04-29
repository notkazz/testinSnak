
/*
Decoder Class for decoding from romans to integers.
    Methods:
        void Decoder()
        int romansToInt(String)
        int getInt(char)
*/

public class Decoder {

    public void Decoder(){ 
    }

    /*
        romanToInt Method

        Summary:
            Convert from Roman Numerals to Int with the following restrictions:

                Repeating Restrictions:
                    I,X,C and M can only be repeated up to 3 times in a row.
                    V,L and D cannot be repeated right after its previus appearance.
                    All Repeating operations above will return -2 when false.
                
                Subtraction Restrictions:
                    I: can only be subtracted from V and X.
                    X: can only be subtracted from L and C.
                    C: can only be subtracted from D and M.
                    V,L,D and M cannot be subtracted from another number.
                    All Subtraction operations above will return -1 when false.

    */
    public int romanToInt(String roman) {
        if (roman == null || roman.length() == 0) {
            return 0;
        }
    
        int anterior = getInt(roman.charAt(roman.length()-1));
        int atual = 0;
        int repeated = 0;
        int repeatedV = 0;
        int somaAux = anterior;

        /*
        The "for" traverses the String inversely from the element in the (anterior-1) position
        and sums or subtracts the current one(atual) with the previous one(anterior) based on the restrictions.
        */
        for (int i = roman.length() - 2; i >= 0; i --) {
            atual = getInt(roman.charAt(i));

            //Subtraction Restrictions Start.
            if (atual < anterior) {
                if( ((anterior == 5 || anterior == 10) && atual==1)   ||
                    ((anterior == 50|| anterior == 100) && atual==10) ||
                    ((anterior == 500|| anterior == 1000) && atual==100) ){
                        somaAux = somaAux - atual; //Subtraction.
                        anterior = 0;
                }
                else if (atual<anterior){
                    return -1;
                }
            } 
            //Subtraction Restrictions End.

            else if(atual >= anterior) {
                somaAux += atual; //Sum.
                anterior = atual;
            }

            //Repeating Restrictions Start.
            if(anterior==atual) {
                if((atual==1) || (atual==10) || (atual==100) || (atual==1000)){
                    repeated++;
                }
                else if((atual==5) || (atual==50) || (atual==500) ){
                    repeated++;
                }
                else if((repeated>3) || repeatedV>1){
                    return -2; 
                }
                else{
                    repeated = 0;
                }     
                
            }
            //Repeating Restrictions End.
        }
        return somaAux;
    }
    

    /*
    getInt Method
    auxiliary method used in romanToInt.
    Converts one roman character into an int.
    */
    public int getInt(char romanC){
        switch(romanC) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M': 
                return 1000;
            default:
                return -1;
        }
    }

}