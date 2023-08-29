package duke.utils;

import duke.DukeException;
import duke.DukeMissingArgumentException;
import duke.DukeMissingTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done.
     * @param taskNumber The task number of the task to be marked as not done.
     */
    public Task unmarkTask(int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markAsUndone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingTaskException();
        }
    }

    /**
     * Marks a task as not done.
     * @param taskNumber The task number of the task to be marked as done.
     */
    public Task markTask(int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingTaskException();
        }
    }

    public Todo addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);

        return todo;
    }

    public Deadline addDeadline(String description, LocalDateTime dateTime) {
        Deadline deadline = new Deadline(description, dateTime);
        tasks.add(deadline);

        return deadline;
    }

    public Event addEvent(String description, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(description, start, end);
        tasks.add(event);

        return event;
    }

    public Task deleteTask(int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingTaskException();
        }
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            message.append("\t").append(i++).append(". ").append(task).append("\n");
        }
        return message.toString();
    }
}
