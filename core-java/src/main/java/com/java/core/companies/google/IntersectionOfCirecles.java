package com.java.core.companies.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 212590467 on 11/17/16.
 */
public class IntersectionOfCirecles {

    class Circle{
        double x;
        double y;
        double r;

    }

    class Interval implements Comparable<Interval>{
        double start;
        double end;
        int id;

        public Interval(double s, double e, int idx){
            start = s;
            end = e;
            id = idx;
        }

        public int compareTo(Interval i){
            if(start == i.start){
                //return end - i.end;
                return -1;
            }
            //return start - i.start;
            return -1;
        }
    }

    public boolean hasIntersect(Circle[] c){

        if(c == null || c.length == 0){
            throw new IllegalArgumentException();
        }

        Interval[] xRange = new Interval[c.length];
        Interval[] yRange = new Interval[c.length];

        for(int i = 0; i < c.length; i++){
            xRange[i] = new Interval(c[i].x - c[i].r,c[i].x + c[i].r,i);
            yRange[i] = new Interval(c[i].y - c[i].r, c[i].y + c[i].r, i);

        }
        Arrays.sort(xRange);
        Arrays.sort(yRange);

        Set<String> overlaps = new HashSet<>();
        for(int i = 1; i < xRange.length; i++){
            if(xRange[i].start <= xRange[i -1].end){
                int minId = Math.min(xRange[i-1].id, xRange[i].id);
                int maxId = Math.max(xRange[i - 1].id, xRange[i].id);
                overlaps.add(minId +", " + maxId);
            }
        }

        for(int i = 1; i < yRange.length; i++){
            if(yRange[i].start <= yRange[i - 1].end){
                int minId = Math.min(yRange[i -1]. id, yRange[i].id);
                int maxId = Math.max(yRange[i - 1].id, yRange[i].id);
                if(overlaps.contains(minId + ", " + maxId)){
                    return true;
                }
            }
        }
        return false;
    }
}
