/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;

/**
 *
 * @author peter
 */
public class WeaponPart implements EntityPart {
    private int damage;
    private int ammo;
    private boolean enter;
    
    public void setEnter(boolean enter) {
        this.enter = enter;
    }
    
    public WeaponPart(int ammo, int damage) {
        this.damage = damage;
        this.ammo = ammo;
    }
    
    public void attack() {
        if (getAmmo() > 0) {
            // shoot, need bullet system here
        } else {
            System.out.println("No Ammo in weapon");
        }
    }
    
    public int getAmmo() {
        return this.ammo;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (enter) {
            // implement shoot
        }
    }
}
