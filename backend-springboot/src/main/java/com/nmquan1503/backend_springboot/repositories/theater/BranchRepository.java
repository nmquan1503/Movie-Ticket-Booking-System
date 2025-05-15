package com.nmquan1503.backend_springboot.repositories.theater;

import com.nmquan1503.backend_springboot.entities.theater.Branch;
import com.nmquan1503.backend_springboot.repositories.theater.custom.CustomBranchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Short>, CustomBranchRepository {



}
