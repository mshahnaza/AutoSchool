package org.example.autoschool.service;

import org.example.autoschool.entity.Exam;
import org.example.autoschool.entity.Student;

public interface EmailService {
    void notifyUser();
    void sendExamResult(Student student, Exam exam);
}
