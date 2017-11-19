package com.cs7s.webpage.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The unanswered enquiries repository that performs query, insert, and update on Unanswered Enquiries Table.
 */
@Repository
public interface UnansweredEnquiriesRepository extends JpaRepository<UnansweredEnquiries, Long> {

}