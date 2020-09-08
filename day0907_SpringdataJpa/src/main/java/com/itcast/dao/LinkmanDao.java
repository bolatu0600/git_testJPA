package com.itcast.dao;

import com.itcast.domain.Linkman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkmanDao extends JpaRepository<Linkman, Long>,
        JpaSpecificationExecutor<Linkman> {
}
