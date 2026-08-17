package vn.edu.eaut.lab6.store;

import vn.edu.eaut.lab6.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentStore {
    private static final List<Student> students = new ArrayList<>();

    // We do NOT use static block here, because Bai 12 requires initializing in AppContextListener
    
    public static List<Student> findAll() {
        return students;
    }

    public static void add(Student student) {
        students.add(student);
    }
    
    public static void delete(String id) {
        students.removeIf(s -> s.getId().equals(id));
    }
    
    public static void update(Student updated) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(updated.getId())) {
                students.set(i, updated);
                break;
            }
        }
    }
    
    public static Student findById(String id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }
    
    public static List<Student> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return students;
        String lowerKeyword = keyword.toLowerCase();
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    public static int getTotalCount() {
        return students.size();
    }

    public static java.util.Map<String, Long> countByClass() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getClassName() != null ? s.getClassName() : "Chưa xếp lớp",
                        Collectors.counting()
                ));
    }
}
