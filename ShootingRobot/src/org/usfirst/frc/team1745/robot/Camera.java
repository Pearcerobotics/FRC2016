package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Camera {

	private NetworkTable table;
	private double[] area, centerX, centerY, height, width, defaultValue = new double[0];
	
	public Camera() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
	}

	/**
	 * @return the area
	 */
	public double[] getArea() {
		area = table.getNumberArray("area", defaultValue);
		return area;
	}

	/**
	 * @return the centerX
	 */
	public double[] getCenterX() {
		centerX = table.getNumberArray("centerX", defaultValue);
		return centerX;
	}

	/**
	 * @return the centerY
	 */
	public double[] getCenterY() {
		centerY = table.getNumberArray("centerY", defaultValue);
		return centerY;
	}

	/**
	 * @return the height
	 */
	public double[] getHeight() {
		height = table.getNumberArray("height", defaultValue);
		return height;
	}

	/**
	 * @return the width
	 */
	public double[] getWidth() {
		width = table.getNumberArray("width", defaultValue);
		return width;
	}
	
	

}
