package com.nmquan1503.backend_springboot.repositories.theater.custom;

import com.nmquan1503.backend_springboot.entities.theater.Branch;

import java.util.List;

public interface CustomBranchRepository {

    List<Branch> findAllByProvinceId(Short provinceId);

    boolean checkAllBranchIdsExist(List<Short> branchIds);

}
