package com.java.core.careercup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by Yogananda Gowda - 212590467 on 4/2/17.
 *
 * Given a schedule (array of start and end times) for meetings,
 * provide an algorithm that finds the minimum number of rooms
 * needed for the day.
 *
 * https://www.careercup.com/question?id=5142448749674496
 */
public class MeetingSchedule {

    public static void main(String[] args) {
        List<Meeting> meetings = new ArrayList<>();
        int startTime = 9 * 60 + 00;
        int endTime = 9 * 60 + 30;
        System.out.println("Start Time : " + startTime + ", End Time : " + endTime);
        Meeting meeting = new Meeting(startTime, endTime);
        meetings.add(meeting);
        startTime = 9 * 60 + 30;
        endTime = 10 * 60 + 00;
        meeting = new Meeting(startTime, endTime);
        meetings.add(meeting);
        startTime = 9 * 60 + 45;
        endTime = 10 * 60 + 15;
        meeting = new Meeting(startTime, endTime);
        meetings.add(meeting);
        startTime = 9 * 60 + 15;
        endTime = 10 * 60 + 15;
        meeting = new Meeting(startTime, endTime);
        meetings.add(meeting);
        int peak = computeMinimumNoOfRoomsForTheDay(meetings);
        System.out.println("Minimum number of rooms required :: " + peak);
    }

    private static int computeMinimumNoOfRoomsForTheDay(List<Meeting> meetings) {
        if( meetings.size() <= 1 ) return 1;
        Collections.sort(meetings);
        System.out.println(meetings);
        int minNumOfRomms = 0;
        Stack<Meeting> stack = new Stack<>();
        stack.push(meetings.get(0));
        for ( int i = 1; i < meetings.size(); i++ ) {
            while ( !stack.isEmpty() && stack.peek().endTime <= meetings.get(i).startTime ) {
                stack.pop();
            }
            stack.push(meetings.get(i));
            minNumOfRomms = Math.max(minNumOfRomms, (int)stack.size() );
        }
        return minNumOfRomms;
    }

    static class Meeting implements Comparable<Meeting> {
        Integer startTime;
        Integer endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }


        @Override
        public int compareTo(Meeting that) {
            return this.endTime.compareTo(that.endTime);
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    } // End of Meeting
}
