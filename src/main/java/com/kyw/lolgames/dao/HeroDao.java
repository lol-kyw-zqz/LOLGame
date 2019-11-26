package com.kyw.lolgames.dao;

import com.kyw.lolgames.entity.HerosEntity;
import com.kyw.lolgames.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : kangyw
 * @date : 上午 11:41 2019/11/25
 */
public class HeroDao {

//    private Connection connection = null;
//    private PreparedStatement preparedStatement = null;
//    private ResultSet resultSet = null;

//    public HerosEntity queryHeroById(int id){
//        HerosEntity entity = null;
//        String sql = "select * from heros where id=?";
//        connection = DBUtil.getConnection();
//
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1,id);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                entity = new HerosEntity();
//                resultToVO(entity,resultSet);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            DBUtil.close(resultSet,preparedStatement,connection);
//        }
//        return entity;
//    }
//
//    public List<HerosEntity> quereyAllHero(){
//        List<HerosEntity> resList = new ArrayList<>();
//        HerosEntity entity;
//        String sql = "select * from heros";
//        connection = DBUtil.getConnection();
//
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                entity = new HerosEntity();
//                resultToVO(entity,resultSet);
//                resList.add(entity);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            DBUtil.close(resultSet,preparedStatement,connection);
//        }
//        return resList;
//    }
//
//    private void resultToVO(HerosEntity entity,ResultSet resultSet) throws Exception{
//        entity.setId(resultSet.getInt("id"));
//        entity.setName(resultSet.getString("name"));
//        entity.setHp(resultSet.getFloat("hp"));
//        entity.setMp(resultSet.getFloat("mp"));
//        entity.setAd(resultSet.getFloat("ad"));
//        entity.setCritRate(resultSet.getDouble("critRate"));
//        entity.setSkillDescription(resultSet.getString("skillDescription"));
//        entity.setSkillsVal(resultSet.getFloat("skillsVal"));
//        entity.setSkillCost(resultSet.getFloat("skillCost"));
//        entity.setArmor(resultSet.getFloat("armor"));
//        entity.setMagicResistance(resultSet.getFloat("magicResistance"));
//    }

    public HerosEntity queryHeroById(int id){
        HerosEntity entity;
        String sql = "select * from heros where id=?";
        List params = new ArrayList();
        params.add(id);
        List result = JDBCUtils.query(sql, params,HerosEntity.class);
        entity = (HerosEntity) result.get(0);

        return entity;
    }

    public List<HerosEntity> quereyAllHero(){
        String sql = "select * from heros";
        List resList = JDBCUtils.query(sql, null,HerosEntity.class);
        return resList;
    }
}
