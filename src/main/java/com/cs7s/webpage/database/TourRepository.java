package com.cs7s.webpage.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * The tour repository that performs query, insert, and update on Tour List.
 * @author Wong Ngo Yin
 */
@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
	/**
	 * Find tours that match the description while ignoring the description's case.
	 * @param description the description of the desired tours.
	 * @return the list of tours that contain the description.
	 */
	public List<Tour> findByDescriptionContainingIgnoreCase(String description);

	/**
	 * Finds all tours that contain the desired name.
	 * @param name the name of the desired tours.
	 * @return the list of tours that contain the name.
	 */
	@Query(value = "SELECT * FROM tour_list WHERE LOWER(?1) LIKE "
			+ "CONCAT('%', LOWER(name), '%') "
			+ "UNION "
			+ "SELECT * FROM tour_list WHERE LOWER(name) LIKE "
			+ "CONCAT('%', LOWER(?1), '%')",
			nativeQuery = true)
	public List<Tour> findByName(String name);

	/**
	 * Finds all tours that contain the desired tour ID.
	 * @param id the tour ID of the desired tours.
	 * @return the list of tours that contain the tour ID.
	 */
	public List<Tour> findById(String id);
}