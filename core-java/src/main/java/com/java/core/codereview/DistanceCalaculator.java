package com.java.core.codereview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistanceCalaculator {
	
	public static void main(String[] args) {
		List<Address> addresses = new ArrayList<Address>();
		Address address = new Address();
		address.setCityName("Fremont");
		address.setDistance(40.0);
		addresses.add(address);
		
		address = new Address();
		address.setCityName("Sunnyvale");
		address.setDistance(42.0);
		addresses.add(address);

		address = new Address();
		address.setCityName("Oakland");
		address.setDistance(9.0);
		addresses.add(address);
		
		address = new Address();
		address.setCityName("BayFair");
		address.setDistance(22.0);
		addresses.add(address);
		
		System.out.println("Before Sort >>>>>>> ");
		print(addresses);
		sort(addresses);
		System.out.println("After Sort >>>>>>> ");
		print(addresses);
	}
	
	static void print(List<Address> addresses) {
//		for( Address address : addresses ) {
//			System.out.println(address);
//		}
		System.out.println(addresses);
	}
	public static void sort(List<Address> addresses ) {
		Collections.sort(addresses);
	}
	
    static class Address implements Comparable<Address> {
        List<Address> list = new ArrayList<Address>();
        private String cityName;
        private Double distance;
        
        public void setCityName(String cityName) {
            this.cityName = cityName;
            
        }
        
        public String getCityName() {
            return cityName;
        }
        
        public void setDistance(Double distance) {
            this.distance = distance;
        }
        
        public Double getDistance() {
            return distance;
        }
    
		@Override
		public int compareTo(Address other) {
			return this.distance.compareTo(other.distance);
		}

		public String toString() {
			return "[City NAme : " + this.cityName + "; Distance : " + this.distance + "]";
		}
     }

}
