package api.dao;

import java.util.List;

import api.bean.Relation;

public class RelationDAO extends BaseDao{
	public List<Relation> list(int start, int count){
		String sql = "select * from api_relation limit ?,?";
		Object[] params = {start, count};
		return super.query(sql, params, Relation.class);
	}
	
	public int getToal() {
		String sql = "select count(*) from api_relation";
		return super.getTotal(sql, null);
	}
	
	public void add(Relation bean) {
		String sql = "insert into api_relation values(null,?,?,?)";
		Object[] params = {bean.getApiId(), bean.getRelationName(),bean.getRelationValue()};
		super.update(sql, params, Relation.class);
	}
	
	public void delete(int id) {
		String sql = "delete from api_relation where id = ?";
		Object[] params = {id};
		super.update(sql, params, Relation.class);
	}
	
	public void update(Relation bean) {
		String sql = "update api_relation set relation_name = ? and relation_value =? where id = ?";
		Object[] params = {bean.getRelationName(),bean.getRelationValue(),bean.getId()};
		super.update(sql, params, Relation.class);
	}
}
