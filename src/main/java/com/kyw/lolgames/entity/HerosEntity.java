package com.kyw.lolgames.entity;

import lombok.Data;

/**
 * @author : kangyw
 * @date : 下午 15:07 2019/11/20
 */
@Data
public class HerosEntity {

    /**英雄名称*/
    private String name;

    /**血量*/
    private float hp;

    /**蓝量*/
    private float mp;

    /**攻击力*/
    private float ad;

    /**暴击率*/
    private double critRate;

    /**技能描述*/
    private String skillDescription;

    /**技能伤害*/
    private float skillsVal;

    /**技能消耗*/
    private float skillCost;

    /**护甲*/
    private float armor;

    /**魔抗*/
    private float magicResistance;

    public HerosEntity() {
    }

    public HerosEntity(String name, float hp, float mp, float ad, double critRate,
                       String skillDescription, float skillsVal, float skillCost, float armor, float magicResistance) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.ad = ad;
        this.critRate = critRate;
        this.skillDescription = skillDescription;
        this.skillsVal = skillsVal;
        this.skillCost = skillCost;
        this.armor = armor;
        this.magicResistance = magicResistance;
    }


    /**
     * 英雄被攻击
     */
    public float beAttacked(float attackAD){
        return hp = this.hp - attackAD;
    }

    /**
     * 英雄使用技能，mp减少
     */
    public void releaseSkills(HerosEntity enemy){
        if(this.getMp() >= this.getSkillCost()){
            System.out.println(this.getName()+"使用了技能：'"+this.getSkillDescription()
                    +"',对"+enemy.getName()+"造成了'"+this.getSkillsVal()+"'点伤害!");
            enemy.setHp(enemy.getHp()-this.skillsVal);
            this.setMp(this.getMp()-this.getSkillCost());
        }else{
            System.out.println("MP值不够，无法发动技能！本次为普通攻击！");
            enemy.setHp(enemy.getHp()-this.getAd());
        }
    }
}
