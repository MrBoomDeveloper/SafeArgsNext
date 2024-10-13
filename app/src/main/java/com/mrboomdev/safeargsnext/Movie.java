package com.mrboomdev.safeargsnext;

import androidx.annotation.NonNull;

import java.io.Serial;
import java.io.Serializable;

public class Movie implements Serializable {
	@Serial
	private static final long serialVersionUID = 1;
	public String name;
	
	@NonNull
	@Override
	public String toString() {
		return name;
	}
}