package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TuanInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TuanInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TuanInfoRepository extends JpaRepository<TuanInfo, Long> {

}
