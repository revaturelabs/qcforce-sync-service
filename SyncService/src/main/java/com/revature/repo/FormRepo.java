package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.domain.Form;


@Repository
public interface FormRepo extends JpaRepository<Form,Integer>{

}