package com.ikramdg.cinema.main;

import com.ikramdg.cinema.domain.Cinema;

public class DriverCinema {
	
	public static void main(String[] args) {
		Cinema cinema = new Cinema();
		cinema.buildRoom().showMenu();
	}

}
