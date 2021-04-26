/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.weapon;

/**
 *
 * @author peter
 */
public interface IWeaponSPI {
    Weapon createWeapon();
    void attack(Weapon weapon);
    void destroyWeapon(Weapon weapon);
}
