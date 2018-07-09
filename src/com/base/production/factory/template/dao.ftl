package ${packageName}.${pak};

import java.util.List;

import ${packageName}.model.${domainName?cap_first};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * ${description}
 * @time	${date}
 */
 @Repository("${domainName}Dao")
public class ${domainName?cap_first}DAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<${domainName?cap_first}> get${domainName?cap_first}List(${domainName?cap_first} ${domainName}) {
		List<${domainName?cap_first}> list = sqlMapClient.queryForList("${domainName?cap_first}.get${domainName?cap_first}List", ${domainName});
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<${domainName?cap_first}> get${domainName?cap_first}BaseList(${domainName?cap_first} ${domainName}) {
		return sqlMapClient.queryForList("${domainName?cap_first}.get${domainName?cap_first}", ${domainName});
	}

	public int get${domainName?cap_first}ListCount(${domainName?cap_first} ${domainName}) {
		return (Integer)sqlMapClient.queryForObject("${domainName?cap_first}.get${domainName?cap_first}ListCount", ${domainName});
	}
	
	public ${domainName?cap_first} get${domainName?cap_first}(${domainName?cap_first} ${domainName}) {
		return (${domainName?cap_first})sqlMapClient.queryForObject("${domainName?cap_first}.get${domainName?cap_first}", ${domainName});
	}

    public int insert${domainName?cap_first}(${domainName?cap_first} ${domainName}) throws Exception {
        return (Integer)sqlMapClient.insert("${domainName?cap_first}.insert${domainName?cap_first}", ${domainName});
    }

    public int update${domainName?cap_first}(${domainName?cap_first} ${domainName}) throws Exception {
        return sqlMapClient.update("${domainName?cap_first}.update${domainName?cap_first}", ${domainName});
    }
    
    public int remove${domainName?cap_first}(${domainName?cap_first} ${domainName}) throws Exception {
        return sqlMapClient.delete("${domainName?cap_first}.remove${domainName?cap_first}", ${domainName});
    }

}
