/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.weapon;

import dk.group6.common.data.Entity;
import com.badlogic.gdx.files.FileHandle;
import dk.group6.commonWeapon.src.main.java.dk.group6.common.weapon.IWeaponSPI;
import dk.group6.common.services.IGamePluginService;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import com.badlogic.gdx.Gdx;

public class WeaponPlugin extends Entity implements IWeaponSPI, IGamePluginService {
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
        Entity weapon = createWeapon(gameData);
        weaponID = world.addEntity(weapon);
    }

    @Override
    public Entity createWeapon(GameData gameData) {
        FileHandle fH = Gdx.files.internal("../CommonWeapon/src/main/resources/assets/pistol.png");
        Entity weapon = new Weapon(10, 2, fH);

        float x = gameData.getDisplayWidth() / 3;
        float y = gameData.getDisplayHeight() / 3;
        weapon.add(new PositionPart(26.5f, 26.5f));

        return weapon;
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
    public void destroyWeapon(Weapon weapon) {
        stop();
    }

    public int getAmmo() {
        return this.ammo;
    }

    public int getDamage() {
        return this.damage
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
