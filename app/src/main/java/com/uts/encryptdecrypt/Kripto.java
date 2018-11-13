/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uts.encryptdecrypt;

import java.util.regex.Pattern;

/**
 *
 * @author vbn
 */
public class Kripto {
    public static String [] hurufBesar = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", 
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static String [] hurufKecil = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", 
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static String [] angka = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
    public static String [] karakter = new String[] {"~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")",
            "-", "_", "+", "=" , "[", "]", "{", "}", ";", ":", "'", "\"", ",", ".", "<", ">", "/", "\\", "|", "?"};
    
    public static String [] hv = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", 
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", 
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=" , "[", "]", "{", "}", ";", ":", "'", "\"", ",", ".",
            "<", ">", "/", "\\", "|", "?", " "};
    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        // TODO code application logic here
        array2();
    }
    
    public static void array2(){
        String input = "Aku Cinta Kamu";
        String key = "Ksjasls";
        
        checkKarakter(key);
        displayMatriks(matriks(joinArray()));
        System.out.println(encrypt(key, input));
        
        checkKarakter(key);
        System.out.println(decrypt(key, encrypt(key, input)));
    }
    
    public static String encrypt(String key, String input){
        key = deleteKarakter(key).toString();
        return encrypt(matriks(joinArray()), checkKey(key, input), input);
    }
    
    public static String decrypt(String key, String encrypt){
        key = deleteKarakter(key).toString();
        return decrypt(matriks(joinArray()), checkKey(key, encrypt), encrypt);
    }
    
    public static String decrypt(String[][] arr, String key, String encrypt){
        String str = "";
        int vertical = 0; int horizontal = 0;
        for (int i = 0; i < encrypt.length(); i++) {
            for (int j = 0; j < hv.length; j++) {
                if (String.valueOf(key.charAt(i)).equals(hv[j])){
                    vertical = j;
                }
            }
            
            for (int k = 0; k < 95; k++) {
                if (String.valueOf(encrypt.charAt(i)).equals(arr[vertical][k])){
                    horizontal = k;
                }
            }
            
            str = str + "" + hv[horizontal];
        }
        return str;
    }
    
    public static String checkKey(String key, String input){
        String str = "";
        if (key.length() < input.length()){
            str = key;
            int i = 0;
            while (str.length() < input.length()){
                str = str + "" + String.valueOf(str.charAt(i));
                i++;
            }
        } else {
            str = key;
        }
        return str;
    }
    
    public static String encrypt(String[][] arr, String key, String input){
        String str = "";
        int horizontal = 0, vertical = 0;
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < hv.length; j++) {
                if(String.valueOf(key.charAt(i)).equals(hv[j])){
                    vertical = j;
                }
                if(String.valueOf(input.charAt(i)).equals(hv[j])){
                    horizontal = j;
                }
            }
            str = str + "" + arr[horizontal][vertical];
        }
        
        return str;
    }
    
    public static String[][] matriks(String[]arr){
        String [][] matriks = new String [95][95];
//        arr[94] = String.valueOf(key.charAt(0));
        for (int i = 0; i < matriks.length; i++) {
            for (int j = 0; j < matriks.length; j++) {
                matriks[i][j] = arr[j];
            }
            
            String temp = arr[0];
            for (int j = 1; j < matriks.length; j++) {
                arr[j-1] = arr[j];
            }
            arr[94] = temp;
        }
        
        return matriks;
    }
    
    public static String[] joinArray(){
        String [] gabung = new String[95];
        int length = hurufBesar.length;
        for (int i = 0; i < length; i++) {
            gabung[i] = hurufBesar[i];
        }
        
        length+= hurufKecil.length;
        for (int s = hurufBesar.length; s < length; s++) {
            gabung[s] = hurufKecil[s-hurufBesar.length];
        }
        
        length+=angka.length;
        for (int i = hurufBesar.length+hurufKecil.length; i < length; i++) {
            gabung[i] = angka[i-hurufBesar.length-hurufKecil.length];
        }
        
        length+=karakter.length;
        for (int i = hurufBesar.length+hurufKecil.length+angka.length; i < length; i++) {
            gabung[i] = karakter[i-hurufBesar.length-hurufKecil.length-angka.length];
        }
        
        gabung[94] = " ";
        
        return gabung;
    }
    
    public static void checkKarakter(String key){
        for (int i = key.length()-1; i >= 0; i--) {
            String str = String.valueOf(key.charAt(i));
            if(Pattern.matches("[A-Z]", str)){
                rotate(hurufBesar, str);
            } else if (Pattern.matches("[a-z]", str)){
                rotate(hurufKecil, str);
            }else if (Pattern.matches("[0-9]", str)){
                rotate(angka, str);
            } else {
                if(!str.equals(" ")){
                    rotate(karakter, str);
                }
            }
        }
    }
    
    public static void rotate(String [] arr, String str){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(str)){
                String temp = str;
                for (int j = i-1; j >= 0 ; j--) {
                    arr[j+1] = arr[j];
                }
                arr[0] = temp;
            }
        }
    }
    
    public static void display(String [] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
    
    public static void displayMatriks(String[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    
    public static StringBuilder deleteKarakter(String key){
        StringBuilder build = new StringBuilder(key);
        int i = 0;
        int j = 0;
        while(i < build.length()-1){
            j = i+1;
            while(j < build.length()){
                if (build.charAt(i) == build.charAt(j)){
                    build.deleteCharAt(j);
                }else {
                    j++;
                }
            }
             i++;
        }
        return build;
    }
}
