import java.util.ArrayList;

public class Calculator {
	public static void main(String[] args) {
		ArrayList<Account> accountList = new ArrayList<Account>();
		int option = 0;
		while (option != 7) {
			Calculator.mainMenu();
			option = Helper.readInt("Enter option > ");
			if (option == 1) {
				Calculator.createAccount(accountList);
			} else if (option == 2) {
				Calculator.viewAccount(accountList);
			} else if (option == 3) {
				Calculator.updateCount(accountList);
			} else if (option == 4) {
				Calculator.calOrundum(accountList);
			} else if (option == 5) {
				Calculator.calOPtoOrundum(accountList);
			} else if (option == 6) {
				Calculator.calNumOfPulls(accountList);
			} else if (option == 7){
				System.out.println("Session has Ended!");
			} else {
				System.out.println("Error: Invalid Option Entered");
			}
		}
	}

	// menus
	public static void mainMenu() {
		Helper.line(30, "=");
		System.out.println("1. Create account");
		System.out.println("2. View account info");
		System.out.println("3. Update OP/Orundum count");
		System.out.println("4. Calculate Orundum earnings over time");
		System.out.println("5. Calculate OP to Orundum count");
		System.out.println("6. Calculate Number of Pulls");
		System.out.println("7. End Session");
		Helper.line(30, "=");
	}

	public static void viewAccountMenu() {
		Helper.line(30, "=");
		System.out.println("1. View all accounts");
		System.out.println("2. Account Search");
		Helper.line(30, "=");
	}

	public static void updateCountMenu() {
		Helper.line(30, "=");
		System.out.println("1. Update Orundum Count");
		System.out.println("2. Update OP Count");
		Helper.line(30, "=");
	}

	public static void calOrundumMenu() {
		Helper.line(30, "=");
		System.out.println("1. Include Monthly Card");
		System.out.println("2. No Monthly Card");
		Helper.line(30, "=");
	}
	
	public static void calPullsMenu() {
		Helper.line(30, "=");
		System.out.println("1. Only Orundum");
		System.out.println("2. With OP");
		Helper.line(30, "=");
	}

	// select account
	public static int selectAccount(ArrayList<Account> accountList, String searchInput) {
		int pos = accountList.size();
		for (int i = 0; i < accountList.size(); i++) {
			String username = accountList.get(i).getUsername();
			if (username.equalsIgnoreCase(searchInput)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	// main functions
	public static void createAccount(ArrayList<Account> accountList) {
		String usernameInput = "";
		while (usernameInput == "") {
			usernameInput = Helper.readString("Enter Username > ");
		}
		int orundumInput = Helper.readInt("Enter Orundum Count (or 0)> ");
		int opInput = Helper.readInt("Enter OP Count (or 0)> ");

		accountList.add(new Account(usernameInput, orundumInput, opInput));
		Helper.line(30, "=");
		System.out.println("Your account has been created!");
		Helper.line(30, "=");
	}

	public static void viewAccount(ArrayList<Account> accountList) {
		if (accountList.size() == 0) {
			System.out.println("There are no available accounts.");
		} else {
			Calculator.viewAccountMenu();
			int option = Helper.readInt("Enter Option > ");
			if (option == 1) { // list all accounts
				Helper.line(30, "=");
				for (int i = 0; i < accountList.size(); i++) {
					accountList.get(i).printInfo();
					Helper.line(30, "=");
				}
			} else if (option == 2) { // list only 1 username
				String username = Helper.readString("Enter Full Username > ");
				int pos = Calculator.selectAccount(accountList, username);
				if (pos == accountList.size()) {
					System.out.println("Error: Account Does Not Exist");
				} else {
					accountList.get(pos).printInfo();
				}
			} else {
				System.out.println("Error: Invalid Option Entered");
			}
		}
	}

	public static void updateCount(ArrayList<Account> accountList) {
		String username = Helper.readString("Enter Full Username > ");
		int pos = Calculator.selectAccount(accountList, username);
		if (pos == accountList.size()) {
			System.out.println("Error: Account Does Not Exist");
		} else {
			Calculator.updateCountMenu();
			int option = Helper.readInt("Enter Option > ");
			if (option == 1) {
				// update Orundum count
				int orundumInput = Helper.readInt("Enter New Orundum Count > ");
				accountList.get(pos).setOrundum(orundumInput);
				System.out.println(
						accountList.get(pos).getUsername() + "'s Orundum count has been updated to " + orundumInput);
			} else if (option == 2) {
				// update OP count
				int opInput = Helper.readInt("Enter New OP Count > ");
				accountList.get(pos).setOP(opInput);
				System.out.println(accountList.get(pos).getUsername() + "'s OP count has been updated to " + opInput);
			} else {
				System.out.println("Error: Invalid Option Entered");
			}
		}
	}

	public static void calOrundum(ArrayList<Account> accountList) {
		String username = Helper.readString("Enter Full Username > ");
		int pos = Calculator.selectAccount(accountList, username);
		if (pos == accountList.size()) {
			System.out.println("Error: Account Does Not Exist");
		} else {
			Calculator.calOrundumMenu();
			int option = Helper.readInt("Enter Option > ");
			if (option == 1) {
				int weeks = Helper.readInt("Enter Number of Weeks > ");
				int orundum = Calculator.calWithMonthly(weeks);
				int pulls = Calculator.calPulls(orundum);
				System.out.println("Total Orundum in " + weeks + " Weeks: " + orundum + " / " + pulls + " pulls");
				
				String input = Helper.readString("Add Orundum to Account? (Yes/No) > ");
				if (input.equalsIgnoreCase("yes")) {
					int newOrundum = accountList.get(pos).getOrundum() + orundum;
					accountList.get(pos).setOrundum(newOrundum);
					System.out.println(accountList.get(pos).getUsername()+"'s Orundum count has been updated to "+newOrundum);
				} 
			} else if (option == 2) {
				int weeks = Helper.readInt("Enter Number of Weeks > ");
				int orundum = Calculator.calWithoutMonthly(weeks);
				int pulls = Calculator.calPulls(orundum);
				System.out.println("Total Orundum in " + weeks + " Weeks: " + orundum + " / " + pulls + " pulls");
				
				String input = Helper.readString("Add Orundum to Account? (Yes/No) > ");
				if (input.equalsIgnoreCase("yes")) {
					int newOrundum = accountList.get(pos).getOrundum() + orundum;
					accountList.get(pos).setOrundum(newOrundum);
					System.out.println(accountList.get(pos).getUsername()+"'s Orundum count has been updated to "+newOrundum);
				} 
			} else {
				System.out.println("Error: Invalid Option Entered");
			}
		}
	}

	public static void calOPtoOrundum(ArrayList<Account> accountList) {
		String username = Helper.readString("Enter Full Username > ");
		int pos = Calculator.selectAccount(accountList, username);
		if (pos == accountList.size()) {
			System.out.println("Error: Account Does Not Exist");
		} else {
			String user = accountList.get(pos).getUsername();
			int op = accountList.get(pos).getOP();
			System.out.println(user+" has "+op+" OP");
			int opInput = Helper.readInt("How much OP do you want to convert? > ");
			if (opInput < 0) {
				System.out.println("Error: Input cannot be less than 0");
			} else if (opInput > op) {
				System.out.println("Error: Not enough available OP");
			} else {
				int orundum = Calculator.calOrundum(opInput);
				int pulls = Calculator.calPulls(orundum);
				System.out.println(opInput+" OP = "+orundum+" Orundum / "+pulls+" pulls");
				
				String input = Helper.readString("Update OP and Orundum count? (Yes/No) > ");
				if (input.equalsIgnoreCase("yes")) {
					int newOrundum = accountList.get(pos).getOrundum() + orundum;
					accountList.get(pos).setOrundum(newOrundum);
					int newOP = accountList.get(pos).getOP() - opInput;
					accountList.get(pos).setOP(newOP);
					System.out.println(user+"'s Orundum count has been updated to "+newOrundum);
					System.out.println(user+"'s OP count has been updated to "+newOP);
				} 
			}
		}
	}
	
	public static void calNumOfPulls(ArrayList<Account> accountList) {
		String username = Helper.readString("Enter Full Username > ");
		int pos = Calculator.selectAccount(accountList, username);
		if (pos == accountList.size()) {
			System.out.println("Error: Account Does Not Exist");
		} else {
			Calculator.calPullsMenu();
			int option = Helper.readInt("Enter Option > ");
			if (option == 1) {
				int orundum = accountList.get(pos).getOrundum();
				int pulls = calPulls(orundum);
				System.out.println(accountList.get(pos).getUsername()+" has "+pulls+" pulls");
			} 
			else if (option == 2) {
				int orundum = accountList.get(pos).getOrundum() + calOrundum(accountList.get(pos).getOP());
				int pulls = calPulls(orundum);
				System.out.println(accountList.get(pos).getUsername()+" has "+pulls+" pulls");
			} else {
				System.out.println("Error: Invalid Option Entered");
			}
		}
	}

	// calculators
	public static int calWithMonthly(int weeks) {
		int perWeek = (100 + 200) * 7 + 500 + 1800;
		int calOrundum = weeks * perWeek;
		return calOrundum;
	}

	public static int calWithoutMonthly(int weeks) {
		int perWeek = (100) * 7 + 500 + 1800;
		int calOrundum = weeks * perWeek;
		return calOrundum;
	}

	public static int calPulls(int orundum) {
		int remainder = orundum % 600;
		int pulls = (orundum - remainder) / 600;
		return pulls;
	}
	
	public static int calOrundum(int op) {
		int orundum = (op * 160);
		return orundum;
	}
}
