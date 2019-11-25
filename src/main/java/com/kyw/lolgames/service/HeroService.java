package com.kyw.lolgames.service;

import com.kyw.lolgames.entity.HerosEntity;

import java.util.List;

/**
 * Created by 96300 on 2019/11/23.
 */
public interface HeroService {

    /**根据id查询英雄详情*/
    HerosEntity getHeroInstance(String heroNo);

    /**查询所有英雄*/
    List<HerosEntity> getAllHeroInstance();
}
