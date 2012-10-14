package entities.game;

import entities.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 18:18
 */

public class Tank {
    private int id;
    private String name;
    private int hp;
    private double minAttackStrength;
    private double maxAttackStrength;
    private double attackDistance;
    private double visibleDistance;

    public Tank(){
    }

    public void reInitValues (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getMinAttackStrength() {
        return minAttackStrength;
    }

    public void setMinAttackStrength(double minAttackStrength) {
        this.minAttackStrength = minAttackStrength;
    }

    public double getMaxAttackStrength() {
        return maxAttackStrength;
    }

    public void setMaxAttackStrength(double maxAttackStrength) {
        this.maxAttackStrength = maxAttackStrength;
    }

    public double getAttackDistance() {
        return attackDistance;
    }

    public void setAttackDistance(double attackDistance) {
        this.attackDistance = attackDistance;
    }

    public double getVisibleDistance() {
        return visibleDistance;
    }

    public void setVisibleDistance(double visibleDistance) {
        this.visibleDistance = visibleDistance;
    }

    public double takeDamage (double enemyAttack){
        if (this.hp>enemyAttack){
            this.hp-=enemyAttack;
        }else{
            this.hp=0;
        }
        return this.hp;
    }

    public double attackEnemy(Player enemy){
        //Для оптимизации в дальнейшем вынести рассчёт разницы максимума и минимума в инит класса
        double attackStrength;
        return attackStrength = this.minAttackStrength + (this.maxAttackStrength-this.minAttackStrength)*Math.random();
    }
}
