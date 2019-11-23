package com.kyw.lolgames;

import com.kyw.lolgames.entity.HerosEntity;

/**
 * @author : kangyw
 * @date : 上午 11:22 2019/11/22
 */
public class HeroFactory {

    public static HerosEntity getHeroInstance(String heroNo){

        HerosEntity myHero = null;
        switch (heroNo){
            case "1":
                myHero = new HerosEntity("亚索",650.0f,0f,60f, 30,"狂风绝息斩",50f,0,0,0);
                break;
            case "2":
                myHero = new HerosEntity("伊泽瑞尔",550f,300f,68,0,"精准弹幕",100f,80f,0,0);
                break;
            case "3":
                myHero = new HerosEntity("提莫",500f,400f,50,0,"致盲吹箭",80f,80f,0,0);
                break;
            case "4":
                myHero = new HerosEntity("嘉文四世",680f,250f,60,0,"天崩地裂",100f,100f,0,0);
                break;
            case "5":
                myHero = new HerosEntity("易大师",600f,350f,65f,0,"阿尔法突袭",70f,100f,0,0);
                break;
            case "6":
                myHero = new HerosEntity("努努",680f,380f,45f,0,"绝对零度",120f,150f,0,0);
                break;
            case "7":
                myHero = new HerosEntity("德莱厄斯",650f,300f,58f,0,"大杀四方",100f,150f,0,0);
                break;
            case "8":
                myHero = new HerosEntity("瑞兹",500f,400f,54,0,"符文涌动",80f,50f,0,0);
                break;
            case "9":
                myHero = new HerosEntity();
                break;
            case "10":
                myHero = new HerosEntity();
                break;
            case "11":
                myHero = new HerosEntity();
                break;
            case "12":
                myHero = new HerosEntity();
                break;

                default:
                    break;
        }
        return myHero;
    }
}
