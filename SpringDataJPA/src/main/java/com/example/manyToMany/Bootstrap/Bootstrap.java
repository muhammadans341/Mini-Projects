package com.example.manyToMany.Bootstrap;


import com.example.manyToMany.Entity.Branch;
import com.example.manyToMany.Entity.Subject;
import com.example.manyToMany.Repositories.BranchRepository;
import com.example.manyToMany.Repositories.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Bootstrap implements CommandLineRunner {

    private BranchRepository branchRepository;
    private SubjectRepository subjectRepository;
    public Bootstrap(BranchRepository branchRepository, SubjectRepository studentRepository) {
        this.branchRepository=branchRepository;
        this.subjectRepository=studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Branch branch1 = getBranch1();//CSE Branch in Engineering
        Branch branch2 = getBranch2();//IT Branch in Engineering
        branch1.setSubjects(new ArrayList(){{
            add(getSubject1());
            add(getSubject2());
        }});

        branch2.setSubjects(new ArrayList(){{
            add(getSubject1());
            add(getSubject3());
        }});
        branchRepository.save(branch1);
        branchRepository.save(branch2);

        Branch b =branchRepository.findById(1L).get();
        b.getSubjects();
        for(Subject s: b.getSubjects())
        {
            System.out.println(s.getSubjectName());
        }

    }


    private static Subject getSubject1(){
        Subject subject = new Subject();
        subject.setSubjectName("Software Engineering");
        subject.setSubjectDesc("Apply key aspects of software engineering processes for the development of a complex software system");

        return subject;
    }

    private static Subject getSubject2(){
        Subject subject = new Subject();
        subject.setSubjectName("Distributed System");
        subject.setSubjectDesc("Explore recent advances in distributed computing systems");

        return subject;
    }

    private static Subject getSubject3(){
        Subject subject = new Subject();
        subject.setSubjectName("Business Analysis and Optimization");
        subject.setSubjectDesc("understand the Internal and external factors that impact the business strategy");

        return subject;
    }

    private static Branch getBranch1(){
        Branch branch = new Branch();
        branch.setBranchName("Computer Science and Engineering");
        branch.setBranchShortName("CSE");
        branch.setDescription("CSE department offers courses under ambitious curricula in computer science and computer engineering..");

        return branch;
    }

    private static Branch getBranch2(){
        Branch branch = new Branch();
        branch.setBranchName("Information Technology");
        branch.setBranchShortName("IT");
        branch.setDescription("IT is the business side of computers - usually dealing with databases, business, and accounting");

        return branch;
    }
}