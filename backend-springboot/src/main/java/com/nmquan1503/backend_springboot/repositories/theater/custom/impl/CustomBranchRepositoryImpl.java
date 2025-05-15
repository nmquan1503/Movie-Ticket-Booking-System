package com.nmquan1503.backend_springboot.repositories.theater.custom.impl;

import com.nmquan1503.backend_springboot.entities.theater.Branch;
import com.nmquan1503.backend_springboot.entities.theater.QBranch;
import com.nmquan1503.backend_springboot.repositories.theater.custom.CustomBranchRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomBranchRepositoryImpl implements CustomBranchRepository {

    JPAQueryFactory queryFactory;

    @Override
    public List<Branch> findAllByProvinceId(Short provinceId) {
        QBranch branch = QBranch.branch;
        return queryFactory
                .select(branch)
                .where(branch.ward.district.province.id.eq(provinceId))
                .fetch();
    }

    @Override
    public boolean checkAllBranchIdsExist(List<Short> branchIds) {
        QBranch branch = QBranch.branch;
        Long count = queryFactory
                .select(branch.count())
                .from(branch)
                .where(branch.id.in(branchIds))
                .fetchOne();
        return count != null && count == branchIds.size();
    }

}
