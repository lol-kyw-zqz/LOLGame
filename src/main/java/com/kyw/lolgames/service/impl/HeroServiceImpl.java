package com.kyw.lolgames.service.impl;

import com.kyw.lolgames.dao.HeroDao;
import com.kyw.lolgames.entity.HerosEntity;
import com.kyw.lolgames.service.HeroService;
import com.kyw.lolgames.utils.InterFaceFactory;

import java.util.List;

/**
 * Created by 96300 on 2019/11/23.
 */
public class HeroServiceImpl implements HeroService {

    private  HeroDao heroDao = InterFaceFactory.getInstance(HeroDao.class);

    @Override
    public  HerosEntity getHeroInstance(String heroNo){
        return heroDao.queryHeroById(Integer.valueOf(heroNo));
    }

    @Override
    public  List<HerosEntity> getAllHeroInstance(){
        return heroDao.quereyAllHero();
    }
}
