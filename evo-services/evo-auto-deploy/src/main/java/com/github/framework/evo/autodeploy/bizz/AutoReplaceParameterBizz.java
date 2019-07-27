package com.github.framework.evo.autodeploy.bizz;

import com.github.framework.evo.autodeploy.dao.AutoDeployParameterDao;
import com.github.framework.evo.autodeploy.dao.AutoReplaceParameterDao;
import com.github.framework.evo.autodeploy.entity.AutoDeployParameter;
import com.github.framework.evo.autodeploy.entity.AutoReplaceParameter;
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
public class AutoReplaceParameterBizz {
    @Autowired
    private AutoReplaceParameterDao autoReplaceParameterDao;

    public List<String> findFileName(){
        return autoReplaceParameterDao.findFileName();
    }

    public List<AutoReplaceParameter> findReplaceParam(String fileName,String envirement){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("file_name",fileName);
        map.put("envirement",envirement);
        List<AutoReplaceParameter> list = (List<AutoReplaceParameter>)autoReplaceParameterDao.selectByMap(map);
        return list;
    }
}
