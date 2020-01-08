package com.inno.dao;

import com.inno.pojo.Mobile;
import java.util.Collection;

@Deprecated
public interface MobileDao {
  boolean addMobile(Mobile mobile);

  Mobile getMobileById(Integer id);

  boolean updateMobileById(Mobile mobile);

  boolean deleteMobileById(Integer id);

  void createTable();

  Collection<Mobile> getAllMobile();
}
