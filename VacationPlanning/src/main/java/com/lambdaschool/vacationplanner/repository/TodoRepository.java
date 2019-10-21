package com.lambdaschool.vacationplanner.repository;

import com.lambdaschool.vacationplanner.models.Todos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepository extends CrudRepository<Todos, Long>
{
    Todos findTodosByTitle(String title);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO vacations (todoid, vacaid) VALUES (:todoid, :vacaid)", nativeQuery = true)
    void assignTodoToVacation(long todoid, long vacaid);
}
