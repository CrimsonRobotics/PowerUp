package com.crimsonrobotics.lib;

public class PID{
	public double p = 0; //Default Proportional gain
	public double i = 0; //Default Integral gain
	public double d = 0; //Default derivative gain
	public double f = 0; //Default feedforward gain
	public int iZone = 0; //Default iZone gain (difference before integrator is run)
	public double rampRate = 0; //Default rampRate
	public int profile = 0; //Default PID profile slot on the Talon
	public PID(double p, double i, double d){
		this.p = p;
		this.i = i;
		this.d = d;
	}
	public PID(double p, double i, double d, double f){
		this(p, i, d);
		this.f = f;
	}
	public PID(double p, double i, double d, double f, int iZone){
		this(p,i,d,f);
		this.iZone = iZone;
	}
	public PID(double p, double i, double d, double f, int iZone, double rampRate){
		this(p, i, d, f, iZone);
		this.rampRate = rampRate;
	}
	public PID(double p, double i, double d, double f, int iZone, double rampRate, int profile){
		this(p, i, d, f, iZone, rampRate);
		this.profile = profile;
	}
}