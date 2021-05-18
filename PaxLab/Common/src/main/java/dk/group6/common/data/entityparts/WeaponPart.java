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

    public WeaponPart(int damage, int ammo) {
		this.damage = damage;
		this.ammo = ammo;
    }
    	

	@Override
	public void process(GameData gameData, Entity entity) {

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

	public boolean canFire() {
		return ammo > 0;
	}
	
	public void fire() {
		ammo--;
	} 
	
}
