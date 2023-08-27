package duke.utils;

import duke.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String path;
    File file;

    public Storage(String path) {
        this.path = path;
        this.file = new File(this.path);
    }

    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter filewriter = new FileWriter(this.path);
            for (Task task : tasks) {
                filewriter.write(task.toSaveString() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = new Parser().parseTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
        return tasks;
    }
}
