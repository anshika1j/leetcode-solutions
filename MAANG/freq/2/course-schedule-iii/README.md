# Course Schedule Iii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting` `Heap (Priority Queue)`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
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
```

---

---
## Quick Revision
Given a list of courses with their duration and deadline, find the maximum number of courses that can be taken.
We greedily pick courses by their deadlines and use a max-heap to backtrack when a course exceeds its deadline.

## Intuition
The core idea is to be greedy. If we want to take as many courses as possible, it makes sense to consider courses that finish earlier first. This is because a course with an earlier deadline "locks us in" sooner, potentially preventing us from taking other courses. So, we sort the courses by their deadlines.

Now, as we iterate through the sorted courses, we try to take each course. We maintain a running `timeTaken`. If adding the current course's duration (`course[0]`) to `timeTaken` does not exceed its deadline (`course[1]`), we can happily take it.

The tricky part is when `timeTaken + course[0] > course[1]`. This means we cannot take the current course *and* all the courses we've already committed to. To maximize the number of courses, we should consider dropping a course we've already taken. Which one should we drop? To free up the most time, we should drop the course with the *longest duration* among those we've already taken (including the current one). This is where a max-priority queue comes in handy. It stores the durations of the courses we've taken so far. If we need to drop a course, we remove the largest duration from the priority queue and subtract it from `timeTaken`. This effectively "undoes" taking the longest course, allowing us to potentially fit the current one. The size of the priority queue at the end represents the maximum number of courses we could take.

## Algorithm
1. Sort the `courses` array in ascending order based on their deadlines (`course[1]`).
2. Initialize `timeTaken` to 0. This variable will keep track of the total duration of courses currently selected.
3. Initialize a max-priority queue (`pq`). This priority queue will store the durations of the courses we have selected. The max-heap property ensures we can quickly access and remove the longest duration.
4. Iterate through each `course` in the sorted `courses` array:
    a. Add the current course's duration (`course[0]`) to the `pq`.
    b. Add the current course's duration (`course[0]`) to `timeTaken`.
    c. Check if `timeTaken` exceeds the current course's deadline (`course[1]`).
    d. If `timeTaken > course[1]`:
        i. Remove the largest duration from the `pq` (this is the longest course taken so far).
        ii. Subtract this removed duration from `timeTaken`. This simulates dropping the longest course to make space.
5. After iterating through all courses, the size of the `pq` represents the maximum number of courses that can be taken. Return `pq.size()`.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Sorting by Deadline:** Prioritizing courses that have earlier deadlines is crucial for maximizing the number of courses.
*   **Max-Priority Queue:** Used to efficiently track and remove the longest duration course when a deadline constraint is violated, enabling backtracking.
*   **Time Management:** Balancing the total time spent with the deadlines of courses.

## Common Mistakes
*   **Incorrect Sorting:** Not sorting by deadlines, or sorting by duration instead, which breaks the greedy strategy.
*   **Not Handling Over-deadline Situations:** Failing to have a mechanism (like a max-heap) to remove a previously taken course when a new course cannot fit within its deadline.
*   **Using a Min-Heap:** A min-heap would remove the shortest course, which is not optimal for freeing up time when a deadline is missed. We want to remove the *longest* course to maximize our chances of fitting more courses.
*   **Incorrectly Updating `timeTaken`:** Forgetting to subtract the duration of the removed course from `timeTaken` when a course is dropped.

## Complexity Analysis
- Time: O(N log N) - reason: Sorting the courses takes O(N log N). The loop iterates N times, and each priority queue operation (offer, poll) takes O(log N). Thus, the total time complexity is dominated by sorting.
- Space: O(N) - reason: The priority queue can store up to N course durations in the worst case.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting
import java.util.PriorityQueue; // Import the PriorityQueue class for the heap

class Solution {
    public int scheduleCourse(int[][] courses) {
        // Sort courses by their deadlines in ascending order.
        // This is a greedy approach: consider courses that finish earlier first.
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        // Initialize a max-priority queue to store the durations of courses taken.
        // We use a max-heap so we can easily remove the longest course if needed.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        // Initialize timeTaken to 0. This will track the total duration of courses currently in our schedule.
        int timeTaken = 0;

        // Iterate through each course after sorting by deadline.
        for (int[] course : courses) {
            // Add the duration of the current course to the priority queue.
            pq.offer(course[0]);
            // Add the duration of the current course to the total time taken.
            timeTaken += course[0];

            // Check if the total time taken exceeds the deadline of the current course.
            if (timeTaken > course[1]) {
                // If it exceeds the deadline, we need to drop a course to make space.
                // To maximize the number of courses, we drop the course with the longest duration.
                // The longest duration is at the top of our max-priority queue.
                // Remove the longest duration from the priority queue.
                timeTaken -= pq.poll(); // Subtract the duration of the dropped course from timeTaken.
            }
        }

        // The size of the priority queue at the end represents the number of courses we were able to schedule.
        // These are the courses whose durations remain in the pq.
        return pq.size();
    }
}
```

## Interview Tips
*   **Explain the Greedy Choice:** Clearly articulate why sorting by deadline is the correct greedy strategy.
*   **Justify the Max-Heap:** Explain why a max-heap is necessary to efficiently backtrack by removing the longest duration course.
*   **Walk Through an Example:** Use a small example to demonstrate how the algorithm works, especially the scenario where a course is dropped.
*   **Discuss Edge Cases:** Consider cases like no courses, all courses fitting, or no courses fitting.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the greedy strategy: sort by deadline.
- [ ] Recognize the need for a data structure to manage taken courses: max-priority queue.
- [ ] Implement the sorting correctly.
- [ ] Implement the priority queue logic for adding and removing courses.
- [ ] Ensure `timeTaken` is updated correctly.
- [ ] Verify the final return value is the size of the priority queue.
- [ ] Analyze time and space complexity.

## Similar Problems
*   1353. Maximum Number of Events That Can Be Attended
*   435. Non-overlapping Intervals
*   1235. Maximum Profit in Job Scheduling

## Tags
`Array` `Greedy` `PriorityQueue` `Sorting`
