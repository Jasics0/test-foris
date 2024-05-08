package org.foris;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AttendanceManager {
    public static void processCommand(Map<String, Map<String, Attendance>> registers, ArrayList<String> studentsNames, String line) {
        String[] parts = line.split(" ");

        String command = parts[0].toLowerCase();
        String student = parts[1];

        switch (command) {
            case "student":
                studentsNames.add(student);
                break;
            case "presence":
                if (!studentsNames.contains(student)) {
                    System.out.println("Error: El estudiante " + student + " no existe.");
                    return;
                }
                processPresenceCommand2(registers, parts, student);
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

    private static void processPresenceCommand2(Map<String, Map<String, Attendance>> registers, String[] parts, String student) {
        String room = parts[5];
        Map<String, Attendance> students;
        if (!registers.containsKey(room)) {
            students = new HashMap<>();
            students.put(student, new Attendance());
            registers.put(room, students);
        } else {
            if (registers.get(room).containsKey(student)) {
                students = registers.get(room);
            } else {
                students = registers.get(room);
                students.put(student, new Attendance());
            }
        }

        processPresenceCommand(students, parts, student);
        registers.put(room, students);
    }

    public static String printStudentAttendance(Map<String, Map<String, Attendance>> registers) {

        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, Map<String, Attendance>> entry : registers.entrySet()) {
            String room = entry.getKey();
            Map<String, Attendance> students = entry.getValue();

            output.append("Room ").append(room).append(":\n");

            for (Map.Entry<String, Attendance> studentEntry : students.entrySet()) {
                String student = studentEntry.getKey();
                Attendance attendance = studentEntry.getValue();

                output.append(student).append(": ").append(attendance.getMinutes()).append(" minutes in ").append(attendance.getDays()).append(" days\n");
            }
        }
        return output.toString();
    }
}
