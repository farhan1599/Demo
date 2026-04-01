package com.digitide.psb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitide.psb.model.SignOnRequest;

import java.util.Optional;

@Repository
public interface SignOnRequestRepository extends JpaRepository<SignOnRequest, Long> {
    Optional<SignOnRequest> findByRequestRefNo(String requestRefNo);
    Optional<SignOnRequest> findBySessionId(String sessionID);
    
    
}
