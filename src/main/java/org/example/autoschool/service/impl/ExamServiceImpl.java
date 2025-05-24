package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.ExamDtoRequest;
import org.example.autoschool.dto.response.ExamDto;
import org.example.autoschool.entity.Exam;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.ExamResult;
import org.example.autoschool.enums.ExamType;
import org.example.autoschool.repository.ExamRepository;
import org.example.autoschool.service.AvailableSlotService;
import org.example.autoschool.service.EmailService;
import org.example.autoschool.service.ExamService;
import org.example.autoschool.utils.exception.NoAccessException;
import org.example.autoschool.utils.exception.ObjectNotFoundException;
import org.example.autoschool.utils.exception.OverloadExeption;
import org.example.autoschool.utils.mapper.ExamMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final AvailableSlotService slotService;
    private final EmailService emailService;

    @Override
    public Exam getEntityById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Exam with id: " + id + "not found"));
    }

    @Override
    public ExamDto getDtoById(Long id) {
        return examMapper.toDto(getEntityById(id));
    }

    @Override
    public List<ExamDto> getAllDtos() {
        return examMapper.toDtoList(examRepository.findAll());
    }

    @Override
    public List<ExamDto> getDtosByStudentId(Long id) {
        return examMapper.toDtoList(examRepository.findByStudentId(id));
    }

    @Override
    public List<ExamDto> getDtosBySlotId(Long id) {
        return examMapper.toDtoList(examRepository.findBySlotId(id));
    }

    @Override
    public List<ExamDto> getDtosByInstructorId(Long id) {
        return examMapper.toDtoList(examRepository.findByInstructorId(id));
    }

    @Override
    public List<ExamDto> getDtosByCategory(String category) {
        return examMapper.toDtoList(examRepository.findByCategory(Category.valueOf(category)));
    }

    @Override
    public List<ExamDto> getDtosByExamType(String examType) {
        return examMapper.toDtoList(examRepository.findByExamType(ExamType.valueOf(examType)));
    }

    @Override
    public List<ExamDto> getDtosByDate(String date) {
        return examMapper.toDtoList(examRepository.findByDate(LocalDate.parse(date)));
    }

    @Override
    public List<ExamDto> getDtosByStudentIdAndExamTypeAndResult(Long studentId, String examType, String result) {
        return examMapper.toDtoList(
                examRepository.findByStudentIdAndExamTypeAndResult(
                        studentId, ExamType.valueOf(examType), ExamResult.valueOf(result)));
    }

    @Override
    public List<ExamDto> getDtosByStudentIdAndResult(Long id, String result) {
        return examMapper.toDtoList(examRepository.findByStudentIdAndResult(id, ExamResult.valueOf(result)));
    }

    @Override
    public List<ExamDto> getDtosByInstructorIdAndResult(Long id, String result) {
        return examMapper.toDtoList(examRepository.findByInstructorIdAndResult(id, ExamResult.valueOf(result)));
    }

    @Override
    public List<ExamDto> getDtosByInstructorIdAndDate(Long id, String date) {
        return examMapper.toDtoList(examRepository.findByInstructorIdAndDate(id, LocalDate.parse(date)));
    }

    @Override
    public List<ExamDto> getDtosByCategoryAndDate(String category, String date) {
        return examMapper.toDtoList(examRepository.findByCategoryAndDate(Category.valueOf(category), LocalDate.parse(date)));
    }

    @Override
    public List<ExamDto> getDtosByExamTypeAndDate(String examType, String date) {
        return examMapper.toDtoList(examRepository.findByExamTypeAndDate(ExamType.valueOf(examType), LocalDate.parse(date)));
    }

    @Override
    public List<Exam> getDtosByBeforeExperationDate(Integer dayNumber) {
        LocalDate targetDate = LocalDate.now().plusDays(dayNumber);
        List<Exam> examList = examRepository.findExamsBeforeExpiration(targetDate);
        List<Exam> result = new ArrayList<>();
        for (Exam exam : examList)
            if (exam.getAvailableSlot().getExamDay().getExamType() == ExamType.THEORETICAL)
                if (exam.getResult() == ExamResult.PASSED)
                    result.add(exam);

        return result;
    }

    @Override
    public List<Exam> getDtosByBeforeExamDate(Integer dayNumber) {
        LocalDate targetDate = LocalDate.now().plusDays(dayNumber);
        List<Exam> examList = examRepository.findExamsBeforeExamDay(targetDate);
        List<Exam> result = new ArrayList<>();

        for (Exam exam : examList)
            if (exam.getResult() == ExamResult.PENDING)
                result.add(exam);

        return result;
    }

    @Override
    public ExamDto save(ExamDtoRequest request) {
        Exam exam = examMapper.toEntity(request);
        if (exam.getAvailableSlot().getIsBooked())
            throw new OverloadExeption("This slot already booked");

        if (exam.getAvailableSlot().getExamDay().getExamType() == ExamType.PRACTICAL)
            createPracticalExam(exam);

        examRepository.save(exam);
        if (examRepository.countBySlotId(exam.getAvailableSlot().getId()) < (exam.getAvailableSlot().getMaxStudentNumber()) || examRepository.countBySlotId(exam.getAvailableSlot().getId()).equals(exam.getAvailableSlot().getMaxStudentNumber()))
            slotService.bookSlot(exam.getAvailableSlot().getId());

        return examMapper.toDto(exam);
    }

    @Override
    public ExamDto update(ExamDtoRequest request) {
        Exam exam = examMapper.toEntity(request);
        Exam newExam = getEntityById(exam.getId());

        if (exam.getAvailableSlot().getIsBooked())
            throw new OverloadExeption("This slot already booked");

        if (exam.getAvailableSlot().getExamDay().getExamType() == ExamType.PRACTICAL)
            createPracticalExam(exam);

        newExam.setAvailableSlot(exam.getAvailableSlot());
        newExam.setResult(exam.getResult());
        newExam.setRemarks(exam.getRemarks());
        newExam.setStudent(exam.getStudent());
        newExam.setTakenAt(exam.getTakenAt());
        newExam.setExpirationAt(exam.getExpirationAt());

        examRepository.save(newExam);
        if (examRepository.countBySlotId(exam.getAvailableSlot().getId()).equals(exam.getAvailableSlot().getMaxStudentNumber()))
            slotService.bookSlot(exam.getAvailableSlot().getId());

        return examMapper.toDto(exam);
    }

    @Override
    public ExamDto giveGrade(Long examId, String result, String remarks) {
        Exam exam = getEntityById(examId);
        exam.setResult(ExamResult.valueOf(result));
        exam.setRemarks(remarks);
        examRepository.save(exam);

        List<ExamDto> exams = getDtosByStudentIdAndExamTypeAndResult(exam.getStudent().getId(), "THEORETICAL", "PASSED");
        for (ExamDto examDto : exams)
            examDto.setResult(ExamResult.EXPIRED);

        emailService.sendExamResult(exam.getStudent(), exam);

        return examMapper.toDto(exam);
    }

    private void createPracticalExam(Exam exam) {
        List<ExamDto> dtos = getDtosByStudentIdAndExamTypeAndResult(exam.getStudent().getId(), "THEORETICAL", "PASSED");

        if (dtos == null || dtos.isEmpty()) {
            throw new NoAccessException("You need to pass theoretical exam first");
        }

        if (dtos.get(0).getExpirationAt().isBefore(LocalDate.now())) {
            exam.setResult(ExamResult.EXPIRED);
            throw new NoAccessException("Your theoretical exam has expired");
        }

    }

}
