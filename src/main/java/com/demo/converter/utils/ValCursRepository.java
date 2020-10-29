package com.demo.converter.utils;

import com.demo.converter.entities.ValCurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValCursRepository extends JpaRepository<ValCurs,Integer> {
    @Query("from ValCurs where date = ?1")
    ValCurs getByDate(String date);
}
