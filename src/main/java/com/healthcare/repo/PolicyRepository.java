package com.healthcare.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.healthcare.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Integer> {
	
	
	@Query(" from Policy as p where p.user.id = :userid")
	//public List<Contact> findContactByUser(@Param("userid")int userid);
	public Page<Policy> findPolicyByUser(@Param("userid")int userid,Pageable pageable);//current page and no of recored(contact) in page
	
	
	
	//public List<Contact> findbyNameContaining(String name,User user);
	

}
