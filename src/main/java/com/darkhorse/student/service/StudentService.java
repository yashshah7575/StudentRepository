package com.darkhorse.student.service;

import com.darkhorse.student.exception.RecordNotFoundException;
import com.darkhorse.student.model.StudentEntity;
import com.darkhorse.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<StudentEntity> getAllStudents(){
       List<StudentEntity> studentList = studentRepository.findAll();

      return
              studentList.size() > 0  ?
                      studentList :
                      new ArrayList<StudentEntity>();
    }

    public StudentEntity getStudentById(Long id)
    {
        Optional<StudentEntity> student = studentRepository.findById(id);

        if(student.isPresent()){
            return student.get();
        }
        else{
            throw new RecordNotFoundException("No Student found with id", id);
        }
    }

    public StudentEntity createOrUpdateStudent(StudentEntity entity){
        if(entity.getId() != 0) {
            Optional<StudentEntity> student = studentRepository.findById(entity.getId());
           if (student.isPresent()) {
                StudentEntity s = student.get();
                s.setEmail(entity.getEmail());
                s.setFirstName(entity.getFirstName());
                s.setLastName(entity.getLastName());
                s.setBranch(entity.getBranch());
                s = studentRepository.save(s);
                return s;
            } else {
                entity = studentRepository.save(entity);
                return entity;
            }
        }
        else {
            entity = studentRepository.save(entity);
            return entity;
        }
    }

    public void deleteStudentById(Long id){
        Optional<StudentEntity> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.deleteById(id);
        }
        else{
            throw new RecordNotFoundException("No  Student found with id", id);
        }
    }
}
