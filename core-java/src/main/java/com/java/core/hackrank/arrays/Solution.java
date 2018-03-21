package com.java.core.hackrank.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	
	public static void main(String[] args) {
		String[] cities = new String[9];
		cities[0] = "Alameda,City,Alameda,73812";
		cities[1] = "Anaheim,City,Orange,336265";
		cities[2] = "Bakersfiled,City,Ken,347483";
		cities[3] = "Berkely,City,Alameda,112580";
		cities[4] = "Beverly Hills,City,Los Angles,34109";
		cities[5] = "Coachella,City,Riverside,63812";
		cities[6] = "Costa Mesa,City,Orange,109960";
		cities[7] = "Gilroy,City,Santa Clara,48821";
		cities[8] = "San Jose,City,Santa Clara,945942";
		
		top5Counties(cities);
		//top5AggregatedCounties(cities);
	}
	
	
	static void top5Counties(String[] cities) {
		List<City> citiList = new ArrayList<City>();
		Map<String, City> countyMap = new HashMap<String, City>();
		for( String cityInfo : cities) {
			System.out.println(cityInfo);
			String[] cityData = cityInfo.split(",");
			City city = new  City();
			city.setCounty(cityData[2]);
			city.setPopulation(Integer.parseInt(cityData[3]));
			if( countyMap.containsKey(cityData[2]) ) {
				int pop = countyMap.get(cityData[2]).getPopulation() + city.getPopulation();
				city.setPopulation(pop);
				countyMap.put(cityData[2],city);
			} else {
				countyMap.put(cityData[2], city);
			}
		}
		citiList.addAll(countyMap.values());
		Collections.sort(citiList);
		System.out.println("What is in the MAp :: " + countyMap);
		
		List<City> subList = citiList.subList(0, 5);
		for( City newCity : subList) {
			System.out.println(newCity.getCounty() + " :: " + newCity.getPopulation());
		}
	}
	
	static void top5AggregatedCounties(String[] cities) {

		int top = 1;
		Map<String, Integer> countyMap = new HashMap<String, Integer>();
		
		for( String cityInfo : cities) {
			System.out.println(cityInfo);
			String[] cityData = cityInfo.split(",");	

			if( countyMap.containsKey(cityData[2]) ) {
				countyMap.put(cityData[2], (countyMap.get(cityData[2]) + Integer.parseInt(cityData[3]) ));
			} else {
				countyMap.put(cityData[2], Integer.parseInt(cityData[3]) );
			}
		}
		System.out.println(countyMap);
		Map<Integer, String> orderedCountyMap = new TreeMap<Integer, String>(
													new Comparator<Integer>() {
											            @Override
											            public int compare(Integer obj1, Integer obj2) {
											                return obj2.compareTo(obj1);
											            }
													});

		for( String county : countyMap.keySet() ) {
			orderedCountyMap.put(countyMap.get(county), county);
		}
	
		System.out.println(orderedCountyMap);
		
//		List<Integer> populationList = new ArrayList<Integer>(countyMap.values());
//		//Collections.sort(populationList, Comparators.POPULATION);
//		Collections.sort(populationList, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer obj1, Integer obj2) {
//                return obj2.compareTo(obj1);
//            }
//        });

		
		//By listing top 5 county based on aggregation of cities population under county
		/*Map<String, Integer> orderedCounty = new LinkedHashMap<String, Integer>();
		for( City sortedCity : citiList ) {
			if( orderedCounty.containsKey(sortedCity.getCounty()) ) {
				orderedCounty.put(sortedCity.getCounty(), (orderedCounty.get(sortedCity.getCounty()) + sortedCity.getPopulation()));
			} else {
				orderedCounty.put(sortedCity.getCounty(), sortedCity.getPopulation());
			}
		}
		
		for(String key : orderedCounty.keySet() ) {
			System.out.println(key + " :: " + orderedCounty.get(key));
		}*/
	}

	 	static class Comparators {

	        public static Comparator<Integer> POPULATION = new Comparator<Integer>() {
	            @Override
	            public int compare(Integer obj1, Integer obj2) {
	                return obj2.compareTo(obj1);
	            }
	        };

	        /*public static Comparator<Student> NAME = new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                return o1.name.compareTo(o2.name);
	            }
	        };
	        public static Comparator<Student> AGE = new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                return o1.age - o2.age;
	            }
	        };
	        public static Comparator<Student> NAMEANDAGE = new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                int i = o1.name.compareTo(o2.name);
	                if (i == 0) {
	                    i = o1.age - o2.age;
	                }
	                return i;
	            }
	        };*/
	    }
	 	
	static class City implements Comparable<City>  {
		private String name;
		private String type;
		private String county;
		private Integer population;
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

		public String getCounty() {
			return county;
		}

		public void setCounty(String county) {
			this.county = county;
		}

		public Integer getPopulation() {
			return population;
		}
		public void setPopulation(Integer population) {
			this.population = population;
		}

		@Override
		public int compareTo(City o) {
			return o.getPopulation().compareTo(this.population);//Order by Descending
			//return this.population.compareTo(o.getPopulation()); //Order by Ascending
		}
		@Override
		public String toString() {
			return "City [name=" + name + ", type=" + type + ", county="
					+ county + ", population=" + population + "]";
		}
	}
}
