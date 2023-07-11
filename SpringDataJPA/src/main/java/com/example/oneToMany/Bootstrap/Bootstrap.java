package com.example.oneToMany.Bootstrap;


import com.example.oneToMany.Repositories.BranchRepository;
import com.example.oneToMany.Entity.Branch;
import com.example.oneToMany.Entity.Student;
import com.example.oneToMany.Repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private BranchRepository branchRepository;
    private StudentRepository studentRepository;
    public Bootstrap(BranchRepository branchRepository, StudentRepository studentRepository) {
        this.branchRepository=branchRepository;
        this.studentRepository=studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Set<Student> s = new HashSet<>();
        Student student1 =Student.builder().fname("Muhammad").lname("Ans").contactNo("0322").build();
        Student student2 =Student.builder().fname("Muhammad").lname("Nomi").contactNo("0324").build();

        Branch branch = Branch.builder().branchName("BRANCH")
                .branchShortName("Short name")
                .description("Description")
                .students(s)
                .build();
        student1.setBranch(branch);
        student2.setBranch(branch);
        s.add(student1);
        s.add(student2);
        branch.setStudents(s);
        branchRepository.save(branch);

        Branch result  = branchRepository.findById(1L).get();
        Set<Student>  test= result.getStudents();
        for(Student stud:test){
            System.out.println(stud.getFname());
            System.out.println(stud.getLname());
        }

    }
}