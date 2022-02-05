package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents an Event which is a kind of Task.
 * Encapsulates an additional LocalDate and LocalTime attribute
 * which represents when the Event occur.
 */
public class Event extends Task {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate startDate;
    private LocalTime time;

    /**
     * Creates a new Event that is marked as not done by default.
     * 
     * <p>Accepts a startDateString with pattern "yyyy-MM-dd" or "yyyy-MM-dd HHmm".</p>
     * 
     * @param task The description of the task.
     * @param startDateString The string representation of when the event occurs.
     * @throws DukeException If startDateString does not have the expected pattern.
     */
    public Event (String task, String startDateString) throws DukeException {
        super(task);
        String[] datetime = startDateString.split(" ");
        
        try {
            startDate = LocalDate.parse(datetime[0], DATE_FORMATTER);
            time = datetime.length == 1 ? null : LocalTime.parse(datetime[1], TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Invalid date/time format!" 
                    + " Expected date and/or time in the following formats: \n"
                    + "yyyy-mm-dd | Example: 2022-06-26\n"
                    + "yyyy-mm-dd HHmm | Example: 2022-06-26 2359");
        }
    }

    /**
     * Creates a new Event that is marked as not done.
     * 
     * 
     * @param task The description of the task.
     * @param startDate Represents the date at which the event occurs. 
     * @param time Represents the time (if any) by which the event occurs. 
     */
    public Event(String task, LocalDate startDate, LocalTime time) {
        super(task);
        this.startDate = startDate;
        this.time = time;
    }

    /**
     * Constructor to create an Event instance.
     * 
     * <p>This constructor accepts an additional isDone boolean to initialize 
     * a task that has been marked/unmarked as done. </p>
     * 
     * <p>Accepts a startDate string with pattern "yyyy-MM-dd HHmm" or "yyyy-MM-dd".</p>
     * @param task The description of the task.
     * @param isDone Marks the task as done if true.
     * @param startDate The string representation of when the event occurs.
     * @throws DukeException If startDate does not have the expected pattern.
     */
    public Event (String task, boolean isDone, String startDate) throws DukeException {
        this(task, startDate);
        this.isDone = isDone;
    }

    /**
     * Formats an Event instance to be stored in an external file.
     */
    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        String startDateString = startDate.format(DATE_FORMATTER);
        String timeString = time == null ? "" : " " + time.format(TIME_FORMATTER);
        return String.format("E | %d | %s | %s%s\n", 
                i, this.task, startDateString, timeString);
    }

    /**
     * Returns the string representation of an Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String date = this.startDate.format(dateFormatter);
        String time = this.time == null ? "" : " " + this.time.format(timeFormatter);
        return String.format("[E]%s %s (at: %s%s)", this.statusString(), this.task, date, time);
    }
}
