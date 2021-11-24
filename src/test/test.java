package test;

import java.util.ArrayList;
import java.util.Iterator;

public class test {
    static void aa(int a[]){
        a[1]=13;
        
    }
    public static void main(String args[]){
        int []ar={1,2,3};
        
        // int[] arr = new int[3];
        // arr[0] = 10;
        // arr[1] = 11;
        // arr[2] = 12;
        //
        // for(int i = 0; i < 3; i++){
        //     arr[]
        // }
        
        System.out.println(ar[1]);
        aa(ar);
        System.out.println(ar[1]);
    
        ArrayList<String> aL = new ArrayList<>();
        aL.add("John");
        aL.add("Boby");
        aL.add("Sammy");
        aL.add("Jimmy");
    
        Iterator<String> itr = aL.iterator();
        
        
    
    
    }
}
