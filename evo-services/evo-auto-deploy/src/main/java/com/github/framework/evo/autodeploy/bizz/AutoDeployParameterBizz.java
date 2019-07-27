package com.github.framework.evo.autodeploy.bizz;

import com.github.framework.evo.autodeploy.dao.AutoDeployParameterDao;
import com.github.framework.evo.autodeploy.entity.AutoDeployParameter;
import com.github.framework.evo.base.bizz.BaseBizz;
import com.github.framework.evo.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@Service
public class AutoDeployParameterBizz {
    @Autowired
    private AutoDeployParameterDao autoDeployParameterDao;

    public String findByKey(String key){
        String result = "";
        Map<String,Object> map = new HashMap<>();
        map.put("key",key);
        List<AutoDeployParameter> autoDeployParameter = (List<AutoDeployParameter>) autoDeployParameterDao.selectByMap(map);
        if(autoDeployParameter.size()==1){
            result = autoDeployParameter.get(0).getValue();
        }else{
            throw new BusinessException("通过Key查无数据，或多条数据！"+ key);
        }
        return result;
    }

}
