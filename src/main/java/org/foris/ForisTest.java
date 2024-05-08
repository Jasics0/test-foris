package org.foris;

import java.io.IOException;
import java.util.Map;

public class ForisTest {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                Map<String, Map<String, Attendance>> registers = FileReaderUtil.readAttendanceData(args[0]);
                System.out.println(AttendanceManager.printStudentAttendance(registers));
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Por favor, proporciona el nombre del archivo como argumento.");
        }
    }
}
