package com.ritoinfo.framework.evo.sp.demo.bizz;

import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseMapperBizz;
import com.ritoinfo.framework.evo.sp.demo.dao.StorageDao;
import com.ritoinfo.framework.evo.sp.demo.dto.StorageDto;
import com.ritoinfo.framework.evo.sp.demo.entity.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2018-07-12 13:13
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class StorageBizz extends BaseMapperBizz<StorageDao, Storage, Long, StorageDto> {
}
