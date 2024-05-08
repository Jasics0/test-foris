package org.foris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileReaderUtil {
    public static Map<String,  Map<String, Attendance>> readAttendanceData(String filename) throws IOException {

        Map<String,  Map<String, Attendance>> registers = new HashMap<>();
        ArrayList<String> studentsNames= new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while (line != null) {
                AttendanceManager.processCommand(registers, studentsNames,line);
                line = reader.readLine();
            }
        }

        return registers;
    }
}