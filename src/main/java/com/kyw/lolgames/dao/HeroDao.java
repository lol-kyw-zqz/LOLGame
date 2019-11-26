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
