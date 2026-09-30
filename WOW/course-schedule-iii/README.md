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
This problem asks for the maximum number of courses that can be taken given their durations and deadlines.
We solve this by greedily picking courses that finish earliest, and if a new course exceeds the deadline, we remove the longest course taken so far.

## Intuition
The core idea is to be greedy. We want to take as many courses as possible. If we consider courses in order of their deadlines, it seems intuitive to prioritize those that expire sooner. However, simply picking the earliest expiring course might prevent us from taking other courses later.

The "aha moment" comes when we realize that if adding a new course makes us miss its deadline, we have a choice: either don't take this new course, or take it and drop a previously taken course. To maximize the number of courses, if we must drop a course, we should drop the one that took the *longest* time. This frees up the most time, giving us the best chance to fit in more courses later. A max-heap (priority queue) is perfect for efficiently finding and removing the longest course.

## Algorithm
1. Sort the `courses` array based on the deadline (the second element of each course). This ensures we consider courses in increasing order of their deadlines.
2. Initialize `timeTaken` to 0, representing the total duration of courses currently selected.
3. Initialize a max-priority queue `pq` to store the durations of the courses currently selected. This will help us quickly identify and remove the longest course if needed.
4. Iterate through each `course` in the sorted `courses` array:
    a. Add the current `course`'s duration (`course[0]`) to `timeTaken`.
    b. Add the current `course`'s duration (`course[0]`) to the `pq`.
    c. Check if `timeTaken` exceeds the current `course`'s deadline (`course[1]`).
        i. If it does, it means we cannot finish this course and all previously selected courses by its deadline. To make space, remove the course with the longest duration from our selection. This is done by polling from the `pq` (which stores durations in descending order) and subtracting its duration from `timeTaken`.
5. After iterating through all courses, the size of the `pq` represents the maximum number of courses that can be taken. Return `pq.size()`.

## Concept to Remember
*   **Greedy Algorithms:** Making locally optimal choices at each step with the hope of finding a global optimum.
*   **Priority Queues (Heaps):** Efficient data structures for maintaining an ordered collection and quickly accessing/removing the minimum or maximum element.
*   **Sorting:** Essential for ordering elements to apply greedy strategies effectively.
*   **Time Complexity Optimization:** Understanding how data structures and algorithms impact overall performance.

## Common Mistakes
*   **Incorrect Sorting Order:** Sorting by duration instead of deadline, or by start time if that were a factor.
*   **Not Handling Deadline Violations Correctly:** Failing to remove a course when the deadline is missed, or removing the wrong course (e.g., the shortest instead of the longest).
*   **Using a Min-Heap Instead of a Max-Heap:** A max-heap is crucial to remove the *longest* duration course when a deadline is missed.
*   **Off-by-One Errors in Time Calculation:** Miscalculating `timeTaken` or comparing it incorrectly with deadlines.

## Complexity Analysis
- Time: O(N log N) - reason: Sorting the courses takes O(N log N). Iterating through N courses, each priority queue operation (offer, poll) takes O(log N). Thus, the dominant factor is sorting.
- Space: O(N) - reason: The priority queue can store up to N course durations in the worst case.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting
import java.util.PriorityQueue; // Import the PriorityQueue class for heap operations

class Solution {
    public int scheduleCourse(int[][] courses) {
        // Sort courses by their deadlines in ascending order.
        // This greedy approach prioritizes courses that expire sooner.
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        // Initialize a max-priority queue to store the durations of courses taken.
        // We use a max-heap so we can easily remove the longest course if a deadline is missed.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        // Initialize timeTaken to 0. This variable tracks the total duration of courses currently in our schedule.
        int timeTaken = 0;

        // Iterate through each course after sorting by deadline.
        for (int[] course : courses) {
            int duration = course[0]; // Get the duration of the current course.
            int deadline = course[1]; // Get the deadline of the current course.

            // Add the current course's duration to our total time taken.
            timeTaken += duration;
            // Add the current course's duration to the priority queue.
            pq.offer(duration);

            // If the total time taken exceeds the current course's deadline,
            // it means we cannot complete this course and all previously selected ones by this deadline.
            if (timeTaken > deadline) {
                // To make space and potentially fit more courses, we must remove a course.
                // We remove the course with the longest duration from our current schedule.
                // pq.poll() removes and returns the maximum element (longest duration) from the max-heap.
                timeTaken -= pq.poll();
            }
        }

        // The size of the priority queue at the end represents the maximum number of courses
        // that could be scheduled without violating any deadlines.
        return pq.size();
    }
}
```

## Interview Tips
1.  **Explain the Greedy Choice:** Clearly articulate *why* sorting by deadline and using a max-heap to remove the longest course is the correct greedy strategy. Emphasize that removing the longest course maximizes the remaining time.
2.  **Walk Through an Example:** Use a small example (e.g., 3-4 courses) to trace the algorithm's execution, showing how `timeTaken` and `pq` change. This demonstrates your understanding.
3.  **Discuss Edge Cases:** Consider what happens with empty input, courses with zero duration, or courses with very tight deadlines.
4.  **Justify Data Structure Choice:** Explain why a `PriorityQueue` (specifically a max-heap) is the ideal data structure for efficiently finding and removing the longest duration course.

## Revision Checklist
- [ ] Understand the problem statement: Maximize courses taken within deadlines.
- [ ] Recognize the greedy approach: Sort by deadline.
- [ ] Identify the need for a max-heap: To remove the longest course when a deadline is missed.
- [ ] Implement sorting correctly: `Arrays.sort` with a custom comparator.
- [ ] Implement priority queue operations: `offer` and `poll`.
- [ ] Track `timeTaken` accurately.
- [ ] Handle deadline violations: `if (timeTaken > deadline)`.
- [ ] Return the correct value: `pq.size()`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Course Schedule (LeetCode 207)
*   Course Schedule II (LeetCode 210)
*   Non-overlapping Intervals (LeetCode 435)
*   Maximum Units on a Truck (LeetCode 1710)

## Tags
`Array` `Greedy` `Sorting` `Heap` `Priority Queue`
