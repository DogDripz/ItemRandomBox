package com.ItemBox.AS;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class Animate implements Runnable
{
	ArmorStand s;
    int count;
    double h;
	
	public Animate(ArmorStand stand) {
        s = stand;
        count = 0;
        h = s.getLocation().getY();
	}
	@Override
	public void run() {
    	EulerAngle oldRot = s.getHeadPose();
        EulerAngle newRot = oldRot.add(0, 0.1f, 0);
        s.setHeadPose(newRot);
        Location secLoc2 = s.getLocation();
        secLoc2.setY(h + (0.4*Math.sin(Math.toRadians(count++)))*10 );
        s.teleport(secLoc2);
	}
}
