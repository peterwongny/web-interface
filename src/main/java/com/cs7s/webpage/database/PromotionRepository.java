package com.cs7s.webpage.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> 
{
//	@Query(value = "SELECT DISTINCT description FROM promotion_table",
//		    nativeQuery = true)
//	List<String> findUniqueDescriptions();
//	
//	List<Promotion> findByDescription(String description);
}
