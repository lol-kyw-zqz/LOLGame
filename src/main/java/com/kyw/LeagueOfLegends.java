package com.kyw;

import com.kyw.lolgames.entity.HerosEntity;
import com.kyw.lolgames.service.HeroService;
import com.kyw.lolgames.utils.InterFaceFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

/**
 * @author kyw
 * Created by 96300 on 2019/11/23.
 */
@SpringBootApplication
public class LeagueOfLegends implements CommandLineRunner {
    private HeroService heroService =  InterFaceFactory.getInstance(HeroService.class);

    public static void main(String[] args) {
        SpringApplication.run(LeagueOfLegends.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //初始化亚索
        System.out.println("---欢迎来到英雄联盟---");
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (true) {

            List<HerosEntity> entityList = heroService.getAllHeroInstance();
            for(HerosEntity vo : entityList){
                sb.append(vo.getId()+"."+vo.getName()+" ");
            }
            System.out.println("请选择你的英雄："+sb.toString());
            String line = sc.nextLine();
            boolean isRight = "1".equals(line) || "2".equals(line) || "3".equals(line) || "4".equals(line);
            HerosEntity myHero;
            if(isRight) {
                myHero = heroService.getHeroInstance(line);
            }else{
                System.out.println("输入的数字不正确，GAMEOVER！！！！！！！");
                break;

            }

            System.out.println("请选择敌方英雄："+sb.toString());
            boolean isRight_2 = "1".equals(line) || "2".equals(line) || "3".equals(line) || "4".equals(line) ||
                    "5".equals(line) || "6".equals(line) || "7".equals(line) || "8".equals(line);
            String line2 = sc.nextLine();
            HerosEntity enemyHero = null;
            if(isRight_2) {
                enemyHero = heroService.getHeroInstance(line2);
            }else{
                System.out.println("输入的数字不正确，GAMEOVER！！！！！！！");
                break;
            }

            gameBegin(myHero, enemyHero,sc);
            System.out.println("是否继续：1.继续，2.退出");
            String line3 = sc.nextLine();
            if("2".equals(line3)){
                break;
            }
        }

    }

    /**
     * 对战细节描述
     * @param h1
     * @param h2
     */
    /*public static void gameBegin(HerosEntity h1,HerosEntity h2){
        System.out.println("初始数值，"+h1.getName()+"HP:"+h1.getHp()+",MP:"+h1.getMp()+",AD:"+h1.getAd());
        System.out.println("初始数值，"+h2.getName()+"HP:"+h2.getHp()+",MP:"+h2.getMp()+",AD:"+h2.getAd());
        System.out.println("对局开始，"+h1.getName()+"先手攻击！");
        while (true) {
            float ad1 = critRateDou(h1.getAd(),h1.getCritRate());
            System.out.println("====" + h1.getName() + ":攻击了" + h2.getName() + ",造成了'" + ad1 + "'点伤害");
            h2.beAttacked(ad1);
            h1.releaseSkills(h2);
            System.out.println("====攻击结束，剩余血量：" + h1.getName() + "为" + h1.getHp() + "," + h2.getName() + "为" + h2.getHp());
            System.out.println("=================================================");

            if(h1.getHp()<0){
                System.out.println(h2.getName()+"击杀了"+h1.getName());
                System.out.println("恕我直言你就是个辣鸡~_~╮︶﹏︶╭！！！！");
                break;
            }else if(h2.getHp()<0){
                System.out.println(h1.getName()+"击杀了"+h2.getName());
                System.out.println("哇，你好棒哦=( °∀° )！！！！");
                break;
            }

            float ad2 = critRateDou(h2.getAd(), h2.getCritRate());
            System.out.println("====敌方攻击," + h2.getName() + ":攻击了" + h1.getName() + ",造成了'" + ad2 + "'点伤害");
            h1.beAttacked(ad2);
            h2.releaseSkills(h1);
            System.out.println("====攻击结束，剩余血量：" + h1.getName() + "为" + h1.getHp() + "," + h2.getName() + "为" + h2.getHp());
            System.out.println("======================================================");

            if(h2.getHp()<0){
                System.out.println(h1.getName()+"击杀了"+h2.getName());
                System.out.println("哇，你好棒哦=( °∀° )！！！！");

                break;
            }else if(h1.getHp()<0){
                System.out.println(h2.getName()+"击杀了"+h1.getName());
                System.out.println("恕我直言你就是个辣鸡~_~╮︶﹏︶╭！！！！");
                break;
            }
        }
    }*/

    /**
     * 对战细节描述
     * @param h1
     * @param h2
     */
    public static void gameBegin(HerosEntity h1,HerosEntity h2,Scanner sc){


        System.out.println("初始数值，"+h1.getName()+"HP:"+h1.getHp()+",MP:"+h1.getMp()+",AD:"+h1.getAd());
        System.out.println("初始数值，"+h2.getName()+"HP:"+h2.getHp()+",MP:"+h2.getMp()+",AD:"+h2.getAd());
        System.out.println("对局开始，"+h1.getName()+"先手攻击！");
        while (true) {
            System.out.println("玩家-"+h1.getName()+"-1,请选择①.普通攻击，②.技能");
            String line3 = sc.nextLine();

            boolean flag =  attackDetail(h1,h2,line3);
            if(flag){
                break;
            }

            System.out.println("玩家-"+h2.getName()+"-2,请选择①.普通攻击，②.技能");
            String line4 = sc.nextLine();

            boolean flag2 =  attackDetail(h2,h1,line4);
            if(flag2){
                break;
            }
        }
    }

    private static boolean attackDetail(HerosEntity ownHero,HerosEntity enemyHero,String attackType){
        final String normalAttack = "1";
        final String skillAttack = "2";

        if(normalAttack.equals(attackType)){
            System.out.println("====" + ownHero.getName() + ":攻击了" + enemyHero.getName() + ",造成了'" + ownHero.getAd() + "'点伤害");
            enemyHero.beAttacked(ownHero.getAd());
        }else if(skillAttack.equals(attackType)) {
            ownHero.releaseSkills(enemyHero);
        }else{
            System.out.println("输入数字不正确，本次对局结束！");
            return true;
        }

        System.out.println("====攻击结束，剩余血量：" + ownHero.getName() + "为" + ownHero.getHp() + "," + enemyHero.getName() + "为" + enemyHero.getHp());
        System.out.println("=================================================");

        if(ownHero.getHp()<0){
            System.out.println(enemyHero.getName()+"击杀了"+ownHero.getName());
            System.out.println("哇，你好棒哦=( °∀° )！！！！");
            return true;
        }else if(enemyHero.getHp()<0){
            System.out.println(ownHero.getName()+"击杀了"+enemyHero.getName());
            System.out.println("哇，你好棒哦=( °∀° )！！！！");
            return true;
        }
        return false;
    }
    /**
     * 暴击翻倍
     */
    public static float critRateDou(float ad,double rate){
        double a = 100*Math.random();
        if(a>rate){
            return ad;
        }else{
            return ad*2;
        }

    }
}
