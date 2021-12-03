package org.epam.final_project.reposetories;

import org.epam.final_project.model.User;
import org.epam.final_project.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(@Param("email") String email);

        Page<User> findAllByRole(Role role, Pageable pageable);
    Page<User> findAllByRoleAndStatus(Role role,boolean status,Pageable pageable);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.status=:status WHERE u.email=:email")
    int changeUserStatus(@Param("status") boolean status, @Param("email") String email);

    void deleteByEmail(String email);
}
