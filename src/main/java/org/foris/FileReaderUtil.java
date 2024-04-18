package org.foris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReaderUtil {
    public static Map<String, Attendance> readAttendanceData(String filename) throws IOException {

        Map<String, Attendance> students = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while (line != null) {
                AttendanceManager.processCommand(students, line);
                line = reader.readLine();
            }
        }

        return students;
    }
}