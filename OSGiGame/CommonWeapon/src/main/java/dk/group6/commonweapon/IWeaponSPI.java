/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.commonweapon;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;


/**
 *
 * @author peter
 */
public interface IWeaponSPI {
    public Weapon createWeapon();
    public void attack(Weapon weapon);
    public void destroyWeapon(Weapon weapon);
}
