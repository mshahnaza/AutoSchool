package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.ExamDayDtoRequest;
import org.example.autoschool.dto.response.ExamDayDto;
import org.example.autoschool.entity.ExamDay;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.ExamType;
import org.example.autoschool.repository.ExamDayRepository;
import org.example.autoschool.service.ExamDayService;
import org.example.autoschool.utils.exception.AlreadyExistException;
import org.example.autoschool.utils.exception.ObjectNotFoundException;
import org.example.autoschool.utils.mapper.ExamDayMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamDayServiceImpl implements ExamDayService {
    private final ExamDayRepository examDayRepository;
    private final ExamDayMapper examDayMapper;

    @Override
    public ExamDay getEntityById(Long id) {
        return examDayRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("ExamDay with id: " + id + " not found"));
    }

    @Override
    public ExamDayDto getDtoById(Long id) {
        return examDayMapper.toDto(getEntityById(id));
    }

    @Override
    public List<ExamDayDto> getAllDtos() {
        return examDayMapper.toDtoList(examDayRepository.findAll());
    }

    @Override
    public List<ExamDayDto> getDtoByDate(String date) {
        return examDayMapper.toDtoList(examDayRepository.findByDate(LocalDate.parse(date)));
    }

    @Override
    public List<ExamDayDto> getDtoByCategory(String category) {
        return examDayMapper.toDtoList(examDayRepository.findByCategory(Category.valueOf(category)));
    }

    @Override
    public List<ExamDayDto> getDtoByExamType(String examType) {
        return examDayMapper.toDtoList(examDayRepository.findByExamType(ExamType.valueOf(examType)));
    }

    @Override
    public List<ExamDayDto> getDtoByBranchId(Long id) {
        return examDayMapper.toDtoList(examDayRepository.findByBranchId(id));
    }

    @Override
    public List<ExamDayDto> getByDateAndExamTypeAndCategory(String date, String examType, String category) {
        return examDayMapper.toDtoList(
                examDayRepository.findByDateAndExamTypeAndCategory(
                        LocalDate.parse(date),
                        ExamType.valueOf(examType),
                        Category.valueOf(category)));
    }

    @Override
    public List<ExamDayDto> getByDateAndExamType(String date, String examType) {
        return examDayMapper.toDtoList(
                examDayRepository.findByDateAndExamType(
                        LocalDate.parse(date),
                        ExamType.valueOf(examType)));
    }

    @Override
    public List<ExamDayDto> getByDateAndCategory(String date, String category) {
        return examDayMapper.toDtoList(examDayRepository.findByDateAndCategory(
                LocalDate.parse(date),
                Category.valueOf(category)));
    }

    @Override
    public List<ExamDayDto> getByExamTypeAndCategory(String examType, String category) {
        return examDayMapper.toDtoList(
                examDayRepository.findByExamTypeAndCategory(
                        ExamType.valueOf(examType),
                        Category.valueOf(category)));
    }

    @Override
    public ExamDayDto save(ExamDayDtoRequest request) {
        ExamDay examDay = examDayMapper.toEntity(request);

        if (examDayRepository.existsByDateAndExamTypeAndCategoryAndBranch(
                examDay.getDate(), examDay.getExamType(),
                examDay.getCategory(), examDay.getBranch()))
            throw new AlreadyExistException("Exam day", "date, exam type, category", examDay.getDate() + " "
                    + examDay.getExamType() + " " + examDay.getCategory());

        return examDayMapper.toDto(examDayRepository.save(examDay));
    }

    @Override
    public ExamDayDto update(ExamDayDtoRequest request) {
        ExamDay examDay = examDayMapper.toEntity(request);
        ExamDay newExamDay = getEntityById(examDay.getId());

        newExamDay.setDate(examDay.getDate());
        newExamDay.setExamType(examDay.getExamType());
        newExamDay.setCategory(examDay.getCategory());
        newExamDay.setExamType(examDay.getExamType());
        newExamDay.setMaxStudents(examDay.getMaxStudents());
        newExamDay.setBranch(examDay.getBranch());

        if (examDayRepository.existsByDateAndExamTypeAndCategoryAndBranch(
                examDay.getDate(), examDay.getExamType(),
                examDay.getCategory(), examDay.getBranch()))
            throw new AlreadyExistException("Exam day", "date, exam type, category, branch", examDay.getDate() + " "
                    + examDay.getExamType() + " " + examDay.getCategory() + " " + examDay.getBranch());

        return examDayMapper.toDto(examDayRepository.save(newExamDay));
    }
}
