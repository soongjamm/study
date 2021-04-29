package com.soongjamm.startboot.practice.thread.async_forkjoin;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {
    private int workload = 0;

    public MyRecursiveAction(int workload) {
        this.workload = workload;
    }

    @SneakyThrows
    @Override
    protected void compute() {
        String threadName = Thread.currentThread().getName();

        if(workload > 16) {
            System.out.println("threadName = " + threadName + " work load : " + workload);
            Thread.sleep(1000);
            ArrayList<MyRecursiveAction> subTasks = new ArrayList<>();
            subTasks.addAll(createSubTasks());

            for (MyRecursiveAction subTask : subTasks) {
                subTask.fork();
            }
        } else {
            System.out.println("doing workload myself " + this.workload);
        }
    }

    private List<MyRecursiveAction> createSubTasks() {
        ArrayList<MyRecursiveAction> subTasks = new ArrayList<>();

        MyRecursiveAction action1 = new MyRecursiveAction(workload/2);
        MyRecursiveAction action2 = new MyRecursiveAction(workload/2);

        subTasks.add(action1);
        subTasks.add(action2);

        return subTasks;
    }
}
