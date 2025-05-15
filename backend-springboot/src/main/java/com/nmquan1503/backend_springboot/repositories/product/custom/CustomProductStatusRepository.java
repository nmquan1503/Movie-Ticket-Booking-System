package com.nmquan1503.backend_springboot.repositories.product.custom;

import java.util.List;

public interface CustomProductStatusRepository {

    boolean checkAllProductStatusIdExist(List<Byte> productStatusIds);

}
