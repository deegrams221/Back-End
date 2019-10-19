package com.lambdaschool.vacaplanner.repository;

import com.lambdaschool.vacaplanner.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
    User findByName(String username);

    List<User> findByNameContainingIgnoreCase(String name,
                                                  Pageable pageable);
}
