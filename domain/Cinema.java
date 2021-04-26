package com.ikramdg.cinema.domain;

import java.util.Scanner;

public class Cinema {

	static Scanner input = new Scanner(System.in);
	static MovieRoom movieRoom = new MovieRoom();
	private static final String STATISTICS_DISPLAY_FORMAT = "Number of purchased tickets: %d\nPercentage: %s\nCurrent income: %s\nTotal income: $%d\n";

	public Cinema buildRoom() {
		System.out.println("Enter the number of rows:");
		int rows = input.nextInt();
		System.out.println("Enter the number of seats in each row:");
		int columns = input.nextInt();
		movieRoom = new MovieRoom(rows, columns);
		return this;
	}

	public void showMenu() {
		while (true) {
			System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n");
			switch (input.nextInt()) {
			case 1:
				movieRoom.displaySittingPlan();
				break;
			case 2:
				buyTicket();
				break;
			case 3:
				showStatistics();
				break;
			case 0:
				return;
			}
		}
	}

	private void buyTicket() {
		int row = 0;
		int column = 0;

		do {
			System.out.println("Enter a row number:");
			row = input.nextInt();
			System.out.println("Enter a seat number in that row:");
			column = input.nextInt();
			if (!movieRoom.ensureValidChoice(row, column)) {
				System.out.println("Wrong input!");
			} else if (movieRoom.isReserved(row, column)) {
				System.out.println("That ticket has already been purchased!");
			}
		} while (movieRoom.isReserved(row, column) || !movieRoom.ensureValidChoice(row, column));
		movieRoom.buyTicket(row, column);
		System.out.println("Ticket price : $" + movieRoom.getPriceForSeat(row, column));
	}

	private void showStatistics() {
		System.out.printf(STATISTICS_DISPLAY_FORMAT, movieRoom.getNumberOfPurchasedTickets(),
				movieRoom.getPercentPurchased(), movieRoom.getCurrentIncome(), movieRoom.calculateTotalIncome());

	}

}
