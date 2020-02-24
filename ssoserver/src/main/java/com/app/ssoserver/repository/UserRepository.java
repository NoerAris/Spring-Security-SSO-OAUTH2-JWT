package com.app.ssoserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ssoserver.Entity.MstUser;

public interface UserRepository extends JpaRepository<MstUser, Long> {
	MstUser findByUsername(String username);
}
