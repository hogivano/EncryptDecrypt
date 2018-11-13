/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uts.encryptdecrypt;

import java.util.regex.Pattern;

/**
 *
 * @author Giv
 */
public class Kriptografi {
    
    String [] hurufBesar;
    String [] hurufKecil;
    String [] angka;
    String [] karakter;
    String [] hv;

    String [][] zigzag;

    public void zigzag(int key, String input){
        int awal = key + (key-1);
        int plus = awal-1;
        int count = awal;

        while (count < input.length()){
            count+=plus;
        }

        zigzag = new String[key][count];
    }

    public void zigzagSetEncrypt(String input){

        String plus = "";
        int count = zigzag[0].length;
        int kurang = count - input.length();
        for (int i = 0; i < kurang; i++) {
            plus+="X";
        }
        int j = zigzag.length-1;
        int index = zigzag.length-1;
        input+=plus;
        boolean naik = true;

        for (int k = 0; k < input.length(); k++) {
            if (k == 0){

            } else if (k % index == 0){
                if((k / index)%2 == 0){
                    naik = true;
                } else {
                    naik = false;
                }
            }

            zigzag[j][k] = String.valueOf(input.charAt(k));

            if (naik){
                j--;
            } else {
                j++;
            }
        }
    }

    public void zizagSetDecrypt(String input){
        String plus = "";

        int kurang = zigzag[0].length - input.length();
        for (int i = 0; i < kurang; i++) {
            plus+="X";
        }
        input+=plus;

        int baris = zigzag[0].length;
        int start = zigzag.length-1;
        int end = 0;
        int tambah = 2 * start;
        int j = 0;

        for (int i = 0; i < zigzag.length; i++) {
            if (i == 0){
                while (start < baris){
                    zigzag[i][start] = String.valueOf(input.charAt(j));
                    j++;
                    start+=tambah;
                }
            } else if (i == zigzag.length-1){
                while (end <= baris){
                    zigzag[i][end] = String.valueOf(input.charAt(j));
                    j++;
                    end+=tambah;
                }
            } else {

                while (start < baris){
                    zigzag[i][start-i] = String.valueOf(input.charAt(j));
                    j++;
                    zigzag[i][start+i] = String.valueOf(input.charAt(j));
                    j++;

                    start+=tambah;
                }
            }
            start = zigzag.length-1;
        }
    }

    public String zigzagEncrypt(){
        String str = "";
        for (int i = 0; i < zigzag.length; i++) {
            for (int j = 0; j < zigzag[0].length; j++) {
                if (zigzag[i][j] != null){
                    str+=zigzag[i][j];
                }
            }
        }

        return str;
    }

    public String zigzagDecrypt(){
        String str = "";
        int index = zigzag.length-1;
        int j = zigzag.length-1;
        boolean naik = true;
        for (int k = 0; k < zigzag[0].length; k++) {
            if (k == 0){

            } else if (k % index == 0){
                if((k / index)%2 == 0){
                    naik = true;
                } else {
                    naik = false;
                }
            }

            str+=zigzag[j][k];

            if (naik){
                j--;
            } else {
                j++;
            }
        }

        char [] arrChar = str.toCharArray();
        str = "";

        for (int i = arrChar.length-1; i >= 0 ; i--) {
            if (arrChar[i] != 'X') {
                for (int k = 0; k < i+1; k++) {
                    str = str + String.valueOf(arrChar[k]);
                }
                break;
            }
        }

        return str;
    }

    public void displayZigzag(){
        for (int i = 0; i < zigzag.length; i++) {
            for (int j = 0; j < zigzag[i].length; j++) {
                System.out.print(zigzag[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    public Kriptografi(){
        hurufBesar = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", 
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        hurufKecil = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", 
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        angka = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
        karakter = new String[] {"~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")",
            "-", "_", "+", "=" , "[", "]", "{", "}", ";", ":", "'", "\"", ",", ".", "<", ">", "/", "\\", "|", "?"};
    
        hv = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", 
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", 
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=" , "[", "]", "{", "}", ";", ":", "'", "\"", ",", ".",
            "<", ">", "/", "\\", "|", "?", " "};
    }

    public void keyZigzag(int key, String input){
        int awal = key + (key-1);
        int plus = awal - 1;
        int count = awal;
        while (count < input.length()){
            count+=plus;
        }

        int panjang = input.length();

        zigzag = new String[key][10];
    }
    
    public String encrypt(String key1, String key2, String input){
        key1 = deleteKarakter(key1).toString();
        input = checkInput(input);

        zigzag(Integer.valueOf(key2), input);
        zigzagSetEncrypt(input);
        input = zigzagEncrypt();
        return encrypt(matriks(joinArray()), checkKey(key1, input), input);
    }
    
    public String decrypt(String key1, String key2, String encrypt){
        key1 = deleteKarakter(key1).toString();
        encrypt = checkInput(encrypt);
        encrypt = decrypt(matriks(joinArray()), checkKey(key1, encrypt), encrypt);

        zigzag(Integer.valueOf(key2), encrypt);
        zizagSetDecrypt(encrypt);
        return zigzagDecrypt();
    }
    
    public String decrypt(String[][] arr, String key, String encrypt){
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

    public String checkInput(String input){
        return input.replace("\n", " ");
    }
    
    public String checkKey(String key, String input){
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
    
    public String encrypt(String[][] arr, String key, String input){
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
    
    public String[][] matriks(String[]arr){
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
    
    public String[] joinArray(){
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
    
    public void checkKarakter(String key){
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
    
    public void rotate(String [] arr, String str){
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
    
    public StringBuilder deleteKarakter(String key){
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
    
    public void display(String [] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
    
    public void displayMatriks(String[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
