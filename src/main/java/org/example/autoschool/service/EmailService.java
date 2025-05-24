package org.example.autoschool.service;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.entity.Exam;
import org.example.autoschool.entity.Student;
import org.example.autoschool.entity.User;
import org.example.autoschool.enums.ExamResult;
import org.example.autoschool.enums.ExamType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final ExamService examService;
    private final JavaMailSender mailSender;

    @Scheduled(cron = "0 0 1 * * *")
    public void notifyUser() {
        List<Exam> examList = examService.getDtosByBeforeExperationDate(3);
        if (!examList.isEmpty()) {
            for (Exam exam : examList) {
                String subject = "Only 3 Days Left Before Your Exam Expires!";
                String content = """
                        Your theoretical exam will expire in 3 days.\s
                        If you do not pass the practical \
                        exam within this period, you will be required to retake the theoretical exam.\s
                        
                        Please make sure to \
                        complete the practical exam on time.""";
                sendEmail(exam.getStudent().getStudentUser(), subject, content);
            }
        }

        examList = examService.getDtosByBeforeExperationDate(1);
        if (!examList.isEmpty()) {
            for (Exam exam : examList) {
                String subject = "Only 1 Days Left Before Your Exam Expires!";
                String content = """
                        Your theoretical exam will expire in 1 days.\s
                        If you do not pass the practical \
                        exam within this period, you will be required to retake the theoretical exam.\s
                        
                        Please make sure to \
                        complete the practical exam on time.""";
                sendEmail(exam.getStudent().getStudentUser(), subject, content);
            }
        }

        examList = examService.getDtosByBeforeExamDate(3);
        if (!examList.isEmpty()) {
            for (Exam exam : examList) {
                String subject = "Only 3 Days Left Before Your Exam Date!";
                String content = "Your " + exam.getAvailableSlot().getExamDay().getExamType()
                        + "exam will be after 3 days on " + exam.getAvailableSlot().getExamDay() + " at "
                        + exam.getAvailableSlot().getTime() + ".\n"
                        + "Place: " + exam.getAvailableSlot().getExamDay().getBranch() + ".";
                sendEmail(exam.getStudent().getStudentUser(), subject, content);
            }
        }

        examList = examService.getDtosByBeforeExamDate(1);
        if (!examList.isEmpty()) {
            for (Exam exam : examList) {
                String subject = "Only 1 Days Left Before Your Exam Date!";
                String content = "Your " + exam.getAvailableSlot().getExamDay().getExamType()
                        + "exam will be after 1 days on " + exam.getAvailableSlot().getExamDay() + " at "
                        + exam.getAvailableSlot().getTime() + ".\n"
                        + "Place: " + exam.getAvailableSlot().getExamDay().getBranch() + ".";
                sendEmail(exam.getStudent().getStudentUser(), subject, content);
            }
        }
    }

    public void sendExamResult(Student student, Exam exam) {
        String emailSubject = "Exam Result";
        String emailContent = "Hello, " + student.getStudentUser().getName() + "! This is your exam result!\n" +
                "This exam was taken " + exam.getTakenAt() + "\n" +
                "Instructor: " + exam.getAvailableSlot().getInstructor() + "\n" +
                "Your result: " + exam.getResult() + "\n";

        if (exam.getRemarks() != null)
            emailContent = "Remarks: " + exam.getRemarks() + "\n";

        if (exam.getAvailableSlot().getExamDay().getExamType() == ExamType.THEORETICAL && exam.getResult() == ExamResult.PASSED)
            emailContent = "Expiration Time " + exam.getExpirationAt() + "\n" +
                    "You need to take practical exam before this date or you will need to retake theoretical exam!";

        sendEmail(student.getStudentUser(), emailSubject, emailContent);
    }

    private void sendEmail(User user, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
