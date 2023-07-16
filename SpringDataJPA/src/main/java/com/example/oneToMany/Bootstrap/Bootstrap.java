package com.example.oneToMany.Bootstrap;


import com.example.oneToMany.Repositories.BranchRepository;
import com.example.oneToMany.Entity.Branch;
import com.example.oneToMany.Entity.StudentTest;
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
        Set<StudentTest> s = new HashSet<>();
        StudentTest studentTest1 = StudentTest.builder().fname("Muhammad").lname("Ans").contactNo("0322").build();
        StudentTest studentTest2 = StudentTest.builder().fname("Muhammad").lname("Nomi").contactNo("0324").build();

        Branch branch = Branch.builder().branchName("BRANCH")
                .branchShortName("Short name")
                .description("Description")
                .studentTests(s)
                .build();
        studentTest1.setBranch(branch);
        studentTest2.setBranch(branch);
        s.add(studentTest1);
        s.add(studentTest2);
        branch.setStudentTests(s);
        branchRepository.save(branch);

        Branch result  = branchRepository.findById(1L).get();
        Set<StudentTest>  test= result.getStudentTests();
        for(StudentTest stud:test){
            System.out.println(stud.getFname());
            System.out.println(stud.getLname());
        }

    }
}