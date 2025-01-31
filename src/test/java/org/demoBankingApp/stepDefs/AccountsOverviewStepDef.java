package org.demoBankingApp.stepDefs;

import java.util.ArrayList;

import org.demoBankingApp.pages.AccountsOverviewPage;
import org.demoBankingApp.utilities.AppUtilities;
import org.testng.Assert;

import io.cucumber.java.en.Then;

public class AccountsOverviewStepDef extends AppUtilities {
	
	@Then("user checks accounts balance amount")
	public void user_checks_accounts_balance_amount() {
		accountsOverviewPage = new AccountsOverviewPage(driver);
		int rows = accountsOverviewPage.getRowSize();
		int cols = accountsOverviewPage.getColSize();
		
		System.out.println("rows: " + rows);
		System.out.println("Cols: " + cols);
		getTableContents(rows, cols);
		
		ArrayList<Double> accBal = getAccBalance(rows);
		
		double sum = getTotalBal(accBal);
		String total = accountsOverviewPage.getTotalAmount();
		double num = getTotalAmountNum(total);
		System.out.println(sum);
		System.out.println(num);
		Assert.assertTrue(num == sum);
	}


	private double getTotalBal(ArrayList<Double> accBal) {
		double sum = 0.0;
		for(int i=0; i < accBal.size();i++) {
			sum = sum + accBal.get(i);
		}
		return sum;
	}

	private ArrayList<Double> getAccBalance(int rows) {
		ArrayList<String> bal = new ArrayList<String>();
		ArrayList<Double> accBal = new ArrayList<Double>();
	
		for(int i=0;i<rows;i++) {
			String data = accountsOverviewPage.getColData(i, 1);
			bal.add(data);
		}
		
		for(int i=0; i<bal.size();i++) {
			String numstr = bal.get(i).substring(1);
			double num = Double.parseDouble(numstr);
			accBal.add(num);
		}
		
		return accBal;
	}

	private void getTableContents(int rows, int cols) {
		for(int i = 0; i< cols; i++) {
			String tblheading = accountsOverviewPage.getColHeading(i);
			System.out.print(tblheading + "\t");
		}

		System.out.println();
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				String data = accountsOverviewPage.getColData(i, j);
				System.out.print(data + "\t");
			}
			System.out.println();
		}
	}
}
