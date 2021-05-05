/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.weapon;

import dk.group6.common.data.Entity;
import com.badlogic.gdx.files.FileHandle;
import dk.group6.common.weapon.IWeaponSPI;
import dk.group6.common.weapon.Weapon;
import dk.group6.common.services.IGamePluginService;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import com.badlogic.gdx.Gdx;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.weapon.IWeaponSPI;
import dk.group6.common.weapon.Weapon;

public class WeaponPlugin implements IWeaponSPI, IGamePluginService {
    private String weaponID;
    private FileHandle fH;
    private int damage;
    private int ammo;

    public WeaponPlugin(int ammo, int damage, FileHandle fH) {
        this.damage = damage;
        this.ammo = ammo;
        this.fH = fH;
    }

    @Override
    public void start(GameData gameData, World world) {
        Entity weapon = (Entity) createWeapon(gameData);
        weaponID = world.addEntity(weapon);
    }

    

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(weaponID);
    }

    @Override
    public void attack(Weapon weapon) {
        if (getAmmo() > 0) {
            // shoot, need bullet system here
        } else {
            System.out.println("No Ammo in weapon");
        }
    }

    @Override
    public void destroyWeapon(Weapon weapon, World world) {
        world.removeEntity(weapon);
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
    public Weapon createWeapon(GameData gameData) {
        Weapon weapon = new Weapon();
        
        weapon.add(new PositionPart(0,0));
        weapon.add(new SpritePart("assets/pistol.png", weapon));
        
        return weapon;    
    }


}
