package fi.utu.tech.common;

import java.util.ArrayList;
import java.util.List;

/**
 * You need to modify this file
 */


public class TaskAllocator {

    /**
     * Allocator that creates list of two (2) GradingTask objects with each having half of the given submissions
     * @param submissions The submissions to be allocated
     * @return The two GradingTask objects in a list, each having half of the submissions
     */
    public static List<GradingTask> sloppyAllocator(List<Submission> submissions) {
        List<GradingTask> allocators = new ArrayList<>();

        GradingTask head = new GradingTask();
        GradingTask tail = new GradingTask();

        head.setUngradedSubmissions(submissions.subList(0, submissions.size()/2));
        tail.setUngradedSubmissions(submissions.subList(submissions.size()/2, submissions.size()));

        allocators.add(head);
        allocators.add(tail);

        return allocators;
    }


    
    /**
     * Allocate List of ungraded submissions to tasks
     * @param submissions List of submissions to be graded
     * @param taskCount Amount of tasks to be generated out of the given submissions
     * @return List of GradingTasks allocated with some amount of submissions (depends on the implementation)
     */
    public static List<GradingTask> allocate(List<Submission> submissions, int taskCount) {
        List<GradingTask> allocator = new ArrayList<>();
        for(int i = 0; i < taskCount; i++){
            GradingTask gt = new GradingTask();
            allocator.add(gt);
        }
        int j = 0;
        for(int k = 0; k <= allocator.size(); k++){
            if(j == submissions.size()) break;
            if(k == allocator.size()) k = 0;
            List<Submission> uusiLista = new ArrayList<>();
            uusiLista = allocator.get(k).getUngradedSubmissions();
            uusiLista.add(submissions.get(j));
            allocator.get(k).setUngradedSubmissions(uusiLista);
            j++;
        }
        return allocator;
    }
}
