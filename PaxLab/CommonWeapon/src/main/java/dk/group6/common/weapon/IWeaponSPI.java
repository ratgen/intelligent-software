/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.weapon;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;

/**
 *
 * @author peter
 */
public interface IWeaponSPI {
    String createWeapon(GameData gameData, World world);
    void attack(Weapon weapon, World world);
    void destroyWeapon(Weapon weapon, World world);
}
