package br.com.lambdateam.myaccessjava.repositories;

import br.com.lambdateam.myaccessjava.models.PasswordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordModel, Long> {

    @Query("SELECT p FROM PasswordModel p WHERE p.id = ?1")
    List<PasswordModel> findPasswordById(Long id);

    @Query("SELECT p FROM PasswordModel p WHERE p.userId.id = ?1")
    List<PasswordModel> listAllByUserId(Long userId);

    @Query("SELECT p FROM PasswordModel p WHERE UPPER(trim(p.description)) like %?1% AND p.userId.id = ?2")
    List<PasswordModel> findByDescriptionAndUserId(String description, Long userId);
}