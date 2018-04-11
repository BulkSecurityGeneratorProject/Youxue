package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MessageComment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MessageComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageCommentRepository extends JpaRepository<MessageComment, Long> {

}
