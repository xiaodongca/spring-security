package com.cxd.springsecurity.dao;

import com.cxd.springsecurity.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: cxd
 * Date: 2017/11/25
 * Description:
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long>{

    SysUser findByUsername(String username);

}
