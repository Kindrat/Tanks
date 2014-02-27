package ua.pirateparty.games.tanks.server.entities.game;

import ua.pirateparty.games.tanks.server.entities.player.Player;

public class Tank {
    private long id;
    private int type;
    private String name;
    private int hp;
    private double minAttackStrength;
    private double maxAttackStrength;
    private double attackDistance;
    private double visibleDistance;
    private long games;
    private long kills;
    private long deaths;

    public Tank(){
    }

    public void reInitValues (){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public long getGames() {
        return games;
    }

    public void setGames(long games) {
        this.games = games;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
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
