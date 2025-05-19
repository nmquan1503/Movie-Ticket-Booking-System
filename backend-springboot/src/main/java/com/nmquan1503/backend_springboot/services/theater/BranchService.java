package com.nmquan1503.backend_springboot.services.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.BranchOptionResponse;
import com.nmquan1503.backend_springboot.entities.theater.Branch;
import com.nmquan1503.backend_springboot.mappers.location.ProvinceMapper;
import com.nmquan1503.backend_springboot.mappers.theater.BranchMapper;
import com.nmquan1503.backend_springboot.repositories.theater.BranchRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchService {

    BranchRepository branchRepository;

    BranchMapper branchMapper;
    ProvinceMapper provinceMapper;

    public List<BranchOptionResponse> getBranchOptionsByProvinceId(Short provinceId) {
        List<Branch> branches = branchRepository.findAllByProvinceId(provinceId);
        return branchMapper.toListBranchOptionResponse(branches);
    }

    public List<BranchOptionResponse> getAllBranchOptions() {
        List<Branch> branches = branchRepository.findAll();
        return branchMapper.toListBranchOptionResponse(branches);
    }

    public boolean checkAllBranchIdsExist(List<Short> branchIds) {
        return branchRepository.checkAllBranchIdsExist(branchIds);
    }

    public boolean existsById(Short branchId) {
        return branchRepository.existsById(branchId);
    }

}
