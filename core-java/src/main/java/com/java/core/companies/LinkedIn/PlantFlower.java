package com.java.core.companies.LinkedIn;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.careercup.com/question?id=5119852580700160
 * 
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. 
 * However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
 *  Given a flowerbed (represented as an array containing booleans), 
 *  return if a given number of new flowers can be planted in it without violating the no-adjacent-flowers rule
	Sample inputs
	
	Input: 1,0,0,0,0,0,1,0,0
	3 => true
	4 => false
	
	Input: 1,0,0,1,0,0,1,0,0
	1 => true
	2 => false
	
	input: 0
	1 => true
	2 => false 

*/
public class PlantFlower {

	public static void main(String[] args) {
		
		List<Boolean> flowerbed = new ArrayList<Boolean>();
		flowerbed.add(true);
		flowerbed.add(false);
		flowerbed.add(false);
		flowerbed.add(true);
		flowerbed.add(false);
		flowerbed.add(false);
		flowerbed.add(true);
		flowerbed.add(false);
		flowerbed.add(false);
		
		System.out.println(canPlaceFlowers(flowerbed, 1));
	}
	
	public static boolean canPlaceFlowers(List<Boolean> flowerbed, int numberToPlace) {
        //this.hashCode();
        if (flowerbed == null || flowerbed.isEmpty()) {
            throw new IllegalArgumentException("bed is empty");
        }
 
        if (numberToPlace == 0)
            return true;
 
        if (flowerbed.size() == 1) {
            return !flowerbed.get(0) && numberToPlace <= 1;
        }
 
        int counter = 0;
 
        for (int i = 0; i < flowerbed.size(); i++){
            if (!flowerbed.get(i)) {
                if ((i == 0 && !flowerbed.get(i + 1))
                || (i == flowerbed.size() - 1 && !flowerbed.get(i - 1))
                || (!flowerbed.get(i+1) && !flowerbed.get(i-1)) ){
                    //place the flower
                    flowerbed.set(i, true);
                    counter++;
                    if (counter == numberToPlace)
                        return true;
                }
            }
        }    
 
        return false;
    }
}
