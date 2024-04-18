import org.foris.Attendance;
import org.foris.AttendanceManager;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceManagerTest {

    @Test
    public void testProcessPresenceCommand() {
        Map<String, Attendance> students = new HashMap<>();
        AttendanceManager.processCommand(students, "Student Marco");
        AttendanceManager.processCommand(students, "Presence Marco 1 09:02 10:17 R100");
        AttendanceManager.processCommand(students, "Presence Marco 3 10:58 12:05 R205");
        assertEquals(142, students.get("Marco").getMinutes());
        assertEquals(2, students.get("Marco").getDays());
    }

    @Test
    public void testPrintStudentAttendance() {
        Map<String, Attendance> students = new HashMap<>();
        AttendanceManager.processCommand(students, "Student Marco");
        AttendanceManager.processCommand(students, "Presence Marco 1 09:02 10:17 R100");
        AttendanceManager.processCommand(students, "Presence Marco 3 10:58 12:05 R205");

        String expectedOutput = "Marco: 142 minutes in 2 days\n";
        String output= AttendanceManager.printStudentAttendance(students);

        assertEquals(expectedOutput, output);
    }
}
