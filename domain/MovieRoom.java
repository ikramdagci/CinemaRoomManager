package com.ikramdg.cinema.domain;

public class MovieRoom {

	private int rows;
	private int columns;
	private char[][] sittingPlan;
	private int currentIncome;
	private int numberOfPurchasedTickets;
	private static final int FRONT_HALF_PRICE_FOR_LARGER_ROOMS = 10;
	private static final int BACK_HALF_PRICE_FOR_LARGER_ROOMS = 8;
	private static final int PRICE_FOR_SMALL_ROOMS = 10;

	public MovieRoom() {
		this(7, 8);
	}

	public MovieRoom(int initialRow, int initialColumn) {
		rows = initialRow;
		columns = initialColumn;
		sittingPlan = new char[initialRow + 1][initialColumn + 1];
		createDraft();
		createPlan();
	}

	public void createPlan() {
		for (int i = 1; i < sittingPlan.length; i++) {
			for (int j = 1; j < sittingPlan[i].length; j++) {
				sittingPlan[i][j] = 'S';
			}
		}
	}

	public void displaySittingPlan() {
		System.out.println("Cinema:");
		for (int i = 0; i < sittingPlan.length; i++) {
			for (int j = 0; j < sittingPlan[i].length; j++) {
				System.out.print(sittingPlan[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void createDraft() {
		sittingPlan[0][0] = ' ';
		firstRow();
		firstColumn();

	}

	private void firstRow() {
		for (int i = 1; i < sittingPlan[0].length; i++) {
			sittingPlan[0][i] = (char) (i + 48);
		}
	}

	private void firstColumn() {
		for (int i = 1; i < sittingPlan.length; i++) {
			sittingPlan[i][0] = (char) (i + 48);
		}
	}

	public int calculateTotalIncome() {
		int totalIncome = (isCinemaRoomLarge()) ? calculateTotalIncomeLargerRoom() : calculateTotalIncomeSmallRoom();
		return totalIncome;
	}

	private int calculateTotalIncomeSmallRoom() {
		return rows * columns * 10;
	}

	private int calculateTotalIncomeLargerRoom() {
		int totalIncome = 0;
		totalIncome += (((rows / 2) * columns * 10) + ((Math.round(rows / 2.0)) * columns * 8));
		return totalIncome;
	}

	public boolean isCinemaRoomLarge() {
		return rows * columns > 60;
	}

	public int getPriceForSeat(int row, int column) {
		if (isCinemaRoomLarge()) {
			if (row <= rows / 2) {
				return FRONT_HALF_PRICE_FOR_LARGER_ROOMS;
			} else {
				return BACK_HALF_PRICE_FOR_LARGER_ROOMS;
			}
		}

		return PRICE_FOR_SMALL_ROOMS;
	}

	public void buyTicket(int row, int column) {
		sittingPlan[row][column] = 'B';
		numberOfPurchasedTickets++;
		currentIncome += getPriceForSeat(row, column);
	}

	/**
	 * @return true if seat is reserved, if is not reserved or invalid choice returns false
	 * 
	 * Note: It is logically wrong to return false for an invalid choice but exceptions 
	 * not yet included in the project.
	 */
	public boolean isReserved(int row, int column) {
		if(!ensureValidChoice(row, column)) {
			return false; 
		}
		return sittingPlan[row][column] == 'B';
	}

	public String getCurrentIncome() {
		return String.format("$%d", currentIncome);
	}

	public int getNumberOfPurchasedTickets() {
		return numberOfPurchasedTickets;
	}

	public String getPercentPurchased() {
		double percentage = ((double)numberOfPurchasedTickets / (rows * columns)) * 100;
		return String.format("%.2f%%", percentage);
	}
	
	public boolean ensureValidChoice(int row, int column) {
		if (row > rows || column > columns) {
			return false;
		}
		return true;
		
	}

}
