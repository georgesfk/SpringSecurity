package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.model.DBuser;

public interface DBUserRepository extends JpaRepository<DBuser, Integer> {
	
	public DBuser findByUsername(String username);

}
