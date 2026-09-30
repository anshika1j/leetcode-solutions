class Solution {
    public int scheduleCourse(int[][] courses) {
        int timeTaken = 0;
        Arrays.sort(courses, (a,b)->a[1]-b[1]); // taking earliest expiring courses first
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a); // to remove longest courses, in case of "new course not fitting"
        for(int[] course : courses){
            pq.offer(course[0]); // add duration to pq
            timeTaken += course[0]; 
            if(timeTaken > course[1]) timeTaken -= pq.poll(); // remove longest course till now, including this course
        }  
        return pq.size(); // this will have all the courses that we have taken till now ( and not removed )
    }
}