package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
	    ArrayList<String> inputList = getInputData("inputData.txt");
        ArrayList<bag> bagArrayList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            String tempString = inputList.get(i);
            bagArrayList.add(new bag( tempString.split(" ")[0] +  tempString.split(" ")[1] ) );
            for (int j = 4; j < (tempString.split(" ").length-3)  ; j+=4) {
                String tempBagName = tempString.split(" ")[j]+ " " + tempString.split(" ")[j+1] + tempString.split(" ")[j+2];
                if(tempBagName.substring(2).equals("shinygold")) {
                    bagArrayList.get(i).setContainingShinyGold(true);
                } else {
                    bagArrayList.get(i).addContainedBag(tempBagName);
                }
            }
        }
        //jetzt haben wir ne List mit Bag-Elementen, und mit wlechen Bags die gefüllt sind

        ArrayList<String> bagsContaingShinyGoldList = new ArrayList<>();
        for (int i = 0; i < bagArrayList.size(); i++) {
            if(bagArrayList.get(i).isContainingShinyGold()) {
                if(!alreadyExists(bagsContaingShinyGoldList, bagArrayList.get(i).getColor())) {
                    bagsContaingShinyGoldList.add(bagArrayList.get(i).getColor());
                }
            }
        }
        bagsContaingShinyGoldList = filldaList(bagsContaingShinyGoldList, bagArrayList); //wahrscheinlich ziemlich rechenintensiv und unnötig lang, aber funktioniert
        System.out.println(bagsContaingShinyGoldList.size());

    }

    public static ArrayList<String> filldaList (ArrayList<String> bagsContainingShinyGoldList, ArrayList<bag> bagsArrayList){
        int listSize = bagsContainingShinyGoldList.size();
        for (int i = 0; i < bagsArrayList.size(); i++) {
            if(!alreadyExists(bagsContainingShinyGoldList, bagsArrayList.get(i).getColor())) {
                for (int j = 0; j < bagsArrayList.get(i).getContainedBagsList().size(); j++) {
                    String tempBag = bagsArrayList.get(i).getContainedBagsList().get(j).substring(2);
                    if (alreadyExists(bagsContainingShinyGoldList, tempBag)) {
                        bagsContainingShinyGoldList.add(bagsArrayList.get(i).getColor());
                        break;
                    }
                }
            }
        }
        System.out.println("rev");
        if(bagsContainingShinyGoldList.size() != listSize) {
            bagsContainingShinyGoldList = filldaList(bagsContainingShinyGoldList, bagsArrayList);
        }
        return bagsContainingShinyGoldList;
    }

    public static boolean alreadyExists(ArrayList<String> bagsContainingShinyGoldList, String bagName){
        for (int j = 0; j < bagsContainingShinyGoldList.size(); j++) {
            if(bagsContainingShinyGoldList.get(j).equals(bagName) ) {
                return true;
            }
        }
        return false;
    }
    //standard method for file Input
    public static ArrayList<String> getInputData (String filename) throws Exception{
        File inputData = new File(filename);
        Scanner scanner = new Scanner(inputData);
        ArrayList<String> dataList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            dataList.add(scanner.nextLine());
        }
        return dataList;
    }
}
