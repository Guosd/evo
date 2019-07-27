package com.github.framework.evo.autodeploy.bizz;

import com.github.framework.evo.autodeploy.dao.AutoDeployLogDao;
import com.github.framework.evo.autodeploy.entity.AutoDeployLog;
import com.github.framework.evo.base.bizz.BaseBizz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@Service
public class AutoDeployLogBizz {
    @Autowired
    private AutoDeployLogDao autoDeployLogDao;

    public int insert(AutoDeployLog autoDeployLog){
        return autoDeployLogDao.insert(autoDeployLog);
    }

}
