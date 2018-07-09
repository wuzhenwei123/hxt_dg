package com.sys.manageAdminUser.dao;

import java.util.List;

import com.sys.manageAdminUser.model.ManageAdminUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;

/**
 * @author keeny
 * @time 2015年02月04日 11:09:21
 */
@Repository("manageAdminUserDao")
public class ManageAdminUserDAO extends BaseDao {

	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	/**
	 * query a list
	 */
	public ResponseList<ManageAdminUser> getManageAdminUserList(ManageAdminUser manageAdminUser) {
		List<ManageAdminUser> list = sqlMapClient.queryForList("ManageAdminUser.getManageAdminUserList", manageAdminUser);
		return buildResponseList(list);
	}
	
	/**
	 * query a list
	 */
	public ResponseList<ManageAdminUser> getManageAdminUserPP(ManageAdminUser manageAdminUser) {
		List<ManageAdminUser> list = sqlMapClient.queryForList("ManageAdminUser.getManageAdminUserPP", manageAdminUser);
		return buildResponseList(list);
	}

	/**
	 * query a base list
	 */
	public List<ManageAdminUser> getManageAdminUserBaseList(ManageAdminUser manageAdminUser) {
		return sqlMapClient.queryForList("ManageAdminUser.getManageAdminUser", manageAdminUser);
	}
	
	
	public List<ManageAdminUser> getOtherManageUserList(ManageAdminUser manageAdminUser) {
		return sqlMapClient.queryForList("ManageAdminUser.getAdminUserByLogin", manageAdminUser);
	}
	
	public List<ManageAdminUser> getServiceUser(ManageAdminUser manageAdminUser) {
		return sqlMapClient.queryForList("ManageAdminUser.getServiceUser", manageAdminUser);
	}

	public int getManageAdminUserListCount(ManageAdminUser manageAdminUser) {
		return (Integer) sqlMapClient.queryForObject("ManageAdminUser.getManageAdminUserListCount", manageAdminUser);
	}
	
	public int getManageAdminUserPPCount(ManageAdminUser manageAdminUser) {
		return (Integer) sqlMapClient.queryForObject("ManageAdminUser.getManageAdminUserPPCount", manageAdminUser);
	}
	
	public int checkAdminName(ManageAdminUser manageAdminUser) {
		return (Integer) sqlMapClient.queryForObject("ManageAdminUser.checkAdminName", manageAdminUser);
	}

	public ManageAdminUser getManageAdminUser(ManageAdminUser manageAdminUser) {
		return (ManageAdminUser) sqlMapClient.queryForObject("ManageAdminUser.getManageAdminUser", manageAdminUser);
	}
	
	public ManageAdminUser getAdminUserByLogin(ManageAdminUser manageAdminUser) {
		return (ManageAdminUser) sqlMapClient.queryForObject("ManageAdminUser.getAdminUserByLogin", manageAdminUser);
	}

	public int insertManageAdminUser(ManageAdminUser manageAdminUser) throws Exception {
		return (Integer) sqlMapClient.insert("ManageAdminUser.insertManageAdminUser", manageAdminUser);
	}

	public int updateManageAdminUser(ManageAdminUser manageAdminUser) throws Exception {
		return sqlMapClient.update("ManageAdminUser.updateManageAdminUser", manageAdminUser);
	}
	public int updateDefaultYLGl(ManageAdminUser manageAdminUser) throws Exception {
		return sqlMapClient.update("ManageAdminUser.updateDefaultYLGl", manageAdminUser);
	}
	public int updateDefaultSJGl(ManageAdminUser manageAdminUser) throws Exception {
		return sqlMapClient.update("ManageAdminUser.updateDefaultSJGl", manageAdminUser);
	}

	public int removeManageAdminUser(ManageAdminUser manageAdminUser) throws Exception {
		return sqlMapClient.delete("ManageAdminUser.removeManageAdminUser", manageAdminUser);
	}
	
	public  ManageAdminUser getWxLoginNum(ManageAdminUser manageAdminUser){
		return (ManageAdminUser) sqlMapClient.queryForObject("ManageAdminUser.getWxLoginNum", manageAdminUser);
	}

	/**
	 * 取消关注，解除绑定
	 * @param manageAdminUser
	 * @return
	 */
	public Integer unBindWx(ManageAdminUser manageAdminUser){
		return sqlMapClient.update("ManageAdminUser.unBindWx", manageAdminUser);
	}
	
	public ManageAdminUser getAdminUserByID(Integer adminId){
		return (ManageAdminUser) sqlMapClient.queryForObject("ManageAdminUser.getAdminUserByID", adminId);
	}
	
	/**
	 * query a list
	 */
	public ResponseList<ManageAdminUser> getManageAdminUserList1(ManageAdminUser manageAdminUser) {
		List<ManageAdminUser> list = sqlMapClient.queryForList("ManageAdminUser.getManageAdminUserList1", manageAdminUser);
		return buildResponseList(list);
	}

	/**
	 * query a base list
	 */
	public List<ManageAdminUser> getManageAdminUserBaseList1(ManageAdminUser manageAdminUser) {
		return sqlMapClient.queryForList("ManageAdminUser.getManageAdminUser1", manageAdminUser);
	}

	public int getManageAdminUserListCount1(ManageAdminUser manageAdminUser) {
		return (Integer) sqlMapClient.queryForObject("ManageAdminUser.getManageAdminUserListCount1", manageAdminUser);
	}

	public ManageAdminUser getManageAdminUser1(ManageAdminUser manageAdminUser) {
		return (ManageAdminUser) sqlMapClient.queryForObject("ManageAdminUser.getManageAdminUser1", manageAdminUser);
	}

	public ManageAdminUser getDefaultServicer(ManageAdminUser user) {
		return (ManageAdminUser) sqlMapClient.queryForObject("ManageAdminUser.getDefaultServicer", user);
	}

	public int updateDefaultService(ManageAdminUser manageAdminUser) {
		return sqlMapClient.update("ManageAdminUser.updateDefaultService", manageAdminUser);
	}
}
