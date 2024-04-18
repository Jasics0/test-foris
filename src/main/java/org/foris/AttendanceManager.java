package org.foris;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AttendanceManager {
    public static void processCommand(Map<String, Attendance> students, String line) {
        String[] parts = line.split(" ");

        String command = parts[0].toLowerCase();
        String student = parts[1];

        switch (command) {
            case "student":
                students.put(student, new Attendance());
                break;
            case "presence":
                if (!students.containsKey(student)) {
                    System.out.println("Error: El estudiante " + student + " no existe.");
                    return;
                }
                processPresenceCommand(students, parts, student);
                break;
            default:
                System.out.println("Comando no reconocido.");
        }
    }

    private static void processPresenceCommand(Map<String, Attendance> students, String[] parts, String student) {
        String dayNumber = parts[2];
        String startTime = parts[3];
        String endTime = parts[4];

        LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime end = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));

        if (end.isBefore(start)) {
            System.out.println("Error: La hora de salida es antes de la hora de entrada.");
            return;
        }

        long minutesDifference = java.time.Duration.between(start, end).toMinutes();

        if (minutesDifference >= 5) {
            Attendance attendance = students.get(student);
            attendance.setMinutes(attendance.getMinutes() + (int) minutesDifference);

            if (attendance.getLastDay() != Integer.parseInt(dayNumber)) {
                attendance.setDays(attendance.getDays() + 1);
                attendance.setLastDay(Integer.parseInt(dayNumber));
            }
        }
    }

    public static String printStudentAttendance(Map<String, Attendance> students) {

        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, Attendance> entry : students.entrySet()) {
            output.append(String.format("%s: %d minutes", entry.getKey(), entry.getValue().getMinutes()));
            output.append((entry.getValue().getDays() > 0) ? String.format(" in %d days", entry.getValue().getDays()) : "");
            output.append("\n");
        }
        return output.toString();
    }
}
