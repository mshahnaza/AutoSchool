package org.example.autoschool.repository;

import org.example.autoschool.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
        select t from Token t inner join User u on t.user.id = u.id
        where u.id = :userId and (t.expired = false or t.revoked = false)
        """)
    List<Token> findAllValidTokensByUser(long userId);

    void deleteByToken(String token);

    Optional<Token> findByToken(String token);
}
