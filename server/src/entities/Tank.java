package entities;

import static global.Static.direction.*;

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
    private double speed;
    private int[] locationCoords;

    public Tank(){
        locationCoords = new int[2];
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int[] getLocationCoords() {
        return locationCoords;
    }

    public void setLocationCoords(int[] locationCoords) {
        this.locationCoords = locationCoords;
    }

    public void move(int direction){
        switch (direction){
            case N:
                this.locationCoords[1]+=speed;
                break;
            case S:
                this.locationCoords[1]-=speed;
                break;
            case E:
                this.locationCoords[0]+=speed;
                break;
            case W:
                this.locationCoords[0]-=speed;
                break;
            case NE:
                this.locationCoords[0]+=speed/2;
                this.locationCoords[1]+=speed/2;
                break;
            case NW:
                this.locationCoords[0]-=speed/2;
                this.locationCoords[1]+=speed/2;
                break;
            case SE:
                this.locationCoords[0]+=speed/2;
                this.locationCoords[1]-=speed/2;
                break;
            case SW:
                this.locationCoords[0]-=speed/2;
                this.locationCoords[1]-=speed/2;
                break;
        }
    }

    public double takeDamage (double enemyAttack){
        if (this.hp>enemyAttack){
            this.hp-=enemyAttack;
        }else{
            this.hp=0;
        }
        return this.hp;
    }

    public void attackEnemy(Player enemy){
        Tank enemyTank = enemy.getTank();
        //Для оптимизации в дальнейшем вынести рассчёт разницы максимума и минимума в инит класса
        double attackStrength = this.minAttackStrength + (this.maxAttackStrength-this.minAttackStrength)/Math.random();
        enemyTank.takeDamage(attackStrength);
    }
}
