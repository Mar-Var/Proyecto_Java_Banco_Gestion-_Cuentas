package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import exceptionsProyect.RetirementExceededException;

class ManagementAccountTest {
		CurrentAccount ca1;
		CurrentAccount ca2;
		CurrentAccount ca3;
		
		DepositAccount da1;
		DepositAccount da2;
		DepositAccount da3;
	
	void Case1() {
		ca1= new CurrentAccount("123456", 0, Calendar.getInstance(), 25);
		ca2= new CurrentAccount("123457", 0, Calendar.getInstance(), 35);
		ca3= new CurrentAccount("123458", 0, Calendar.getInstance(), 45);

		
		da1= new DepositAccount("123456", 100, Calendar.getInstance(), 10);
		da2= new DepositAccount("223457",100, Calendar.getInstance(), 10);
		da3= new DepositAccount("323458", 180, Calendar.getInstance(), 10);

	}

	@Test
	void testDeposity() {
		Case1();
		assertTrue(ca1.addCheckBook("123456", "12345678"));
		assertTrue(ca1.addCheckBook("123476", "12345678"));
		assertTrue(ca1.addCheckBook("127456", "12345678"));
	}

	@Test
	void testRetirement() throws RetirementExceededException {
		Case1();
		assertTrue(ca1.retirement(1));
		assertTrue(ca2.retirement(35));
		assertTrue(ca3.retirement(45));
		assertTrue(da1.retirement(25));
		assertTrue(da2.retirement(35));
		assertTrue(da3.retirement(150));
	}

	@Test
	void testFindAccount() throws Exception {
		ManagementAccount ma = new ManagementAccount();
		String[] Ca1=ma.addInformationAcount("123456", "0", "10");
		String[] Ca2=ma.addInformationAcount("123457", "1", "10");
		String[] Ca3=ma.addInformationAcount("123458", "2", "10");
		String[] Da1=ma.addInformationAcount("223456", "0", "10");
		String[] Da2=ma.addInformationAcount("323456", "0", "10");
		String[] Da3=ma.addInformationAcount("423456", "0", "10");
		
		assertTrue( ma.addAccount(ETypeAccount.CURRENT, Ca1));
		assertTrue(ma.addAccount(ETypeAccount.CURRENT, Ca2));
		assertTrue(ma.addAccount(ETypeAccount.CURRENT, Ca3));
		
		assertTrue(ma.addAccount(ETypeAccount.DEPOSIT, Da1));
		assertTrue(ma.addAccount(ETypeAccount.DEPOSIT, Da2));
		assertTrue(ma.addAccount(ETypeAccount.DEPOSIT, Da3));
		
		assertNotNull(ma.findAccount("123456"));
		assertNotNull(ma.findAccount("123457"));
		assertNotNull(ma.findAccount("123458"));
		assertNotNull(ma.findAccount("323456"));
		assertNull(ma.findAccount("3254785"));
		assertNull(ma.findAccount("34785"));
		
		
		
	}

	@Test
	void testFindPosition() throws Exception {
		ManagementAccount ma = new ManagementAccount();
		String[] Ca1=ma.addInformationAcount("123456", "0", "10");
		String[] Ca2=ma.addInformationAcount("123457", "1", "10");
		String[] Ca3=ma.addInformationAcount("123458", "2", "10");
		String[] Da1=ma.addInformationAcount("223456", "0", "10");
		String[] Da2=ma.addInformationAcount("323456", "0", "10");
		String[] Da3=ma.addInformationAcount("423456", "0", "10");
		
		ma.addAccount(ETypeAccount.CURRENT, Ca1);
		ma.addAccount(ETypeAccount.CURRENT, Ca2);
		ma.addAccount(ETypeAccount.CURRENT, Ca3);
		
		ma.addAccount(ETypeAccount.DEPOSIT, Da1);
		ma.addAccount(ETypeAccount.DEPOSIT, Da2);
		ma.addAccount(ETypeAccount.DEPOSIT, Da3);
		
		assertEquals(3, ma.findPosition("223456"));
		
		
	}

	@Test
	void testDeleteAccount() throws Exception {
		
		ManagementAccount ma = new ManagementAccount();
		String[] Ca1=ma.addInformationAcount("123456", "0", "10");
		String[] Ca2=ma.addInformationAcount("123457", "1", "10");
		String[] Ca3=ma.addInformationAcount("123458", "2", "10");
		String[] Da1=ma.addInformationAcount("223456", "0", "10");
		String[] Da2=ma.addInformationAcount("323456", "0", "10");
		String[] Da3=ma.addInformationAcount("423456", "0", "10");
		
		ma.addAccount(ETypeAccount.CURRENT, Ca1);
		ma.addAccount(ETypeAccount.CURRENT, Ca2);
		ma.addAccount(ETypeAccount.CURRENT, Ca3);
		
		ma.addAccount(ETypeAccount.DEPOSIT, Da1);
		ma.addAccount(ETypeAccount.DEPOSIT, Da2);
		ma.addAccount(ETypeAccount.DEPOSIT, Da3);
		
		assertTrue(ma.deleteAccount("123456"));
		
		}

	@Test
	void testAddAccount() throws Exception {
		
		ManagementAccount ma = new ManagementAccount();
		String[] Ca1=ma.addInformationAcount("123456", "0", "10");
		String[] Ca2=ma.addInformationAcount("123457", "1", "10");
		String[] Ca3=ma.addInformationAcount("123458", "2", "10");
		String[] Ca4=ma.addInformationAcount("123459", "3", "10");
		String[] Ca5=ma.addInformationAcount("123451", "4", "10");
		String[] Ca6=ma.addInformationAcount("123452", "5", "10");
		
		
		String[] Da1=ma.addInformationAcount("123456", "0", "10");
		String[] Da2=ma.addInformationAcount("223456", "0", "10");
		String[] Da3=ma.addInformationAcount("323456", "0", "10");
		String[] Da4=ma.addInformationAcount("423456", "0", "10");
		String[] Da5=ma.addInformationAcount("523456", "0", "10");
		
		assertTrue( ma.addAccount(ETypeAccount.CURRENT, Ca1));
		assertTrue(ma.addAccount(ETypeAccount.CURRENT, Ca2));
		assertTrue(ma.addAccount(ETypeAccount.CURRENT, Ca3));
		assertTrue(ma.addAccount(ETypeAccount.CURRENT, Ca4));
		assertTrue(ma.addAccount(ETypeAccount.CURRENT, Ca5));
		assertTrue(ma.addAccount(ETypeAccount.CURRENT, Ca6));
		
		assertFalse(ma.addAccount(ETypeAccount.DEPOSIT, Da1));
		assertTrue(ma.addAccount(ETypeAccount.DEPOSIT, Da2));
		assertTrue(ma.addAccount(ETypeAccount.DEPOSIT, Da3));
		assertTrue(ma.addAccount(ETypeAccount.DEPOSIT, Da4));
		assertTrue(ma.addAccount(ETypeAccount.DEPOSIT, Da5));

	}

	@Test
	void testAddCheckBook() throws Exception {
		ManagementAccount ma = new ManagementAccount();
		String[] Ca1=ma.addInformationAcount("123456", "0", "10");
		String[] Ca2=ma.addInformationAcount("123457", "1", "10");
		String[] Ca3=ma.addInformationAcount("123458", "2", "10");
		String[] Da1=ma.addInformationAcount("223456", "0", "10");
		String[] Da2=ma.addInformationAcount("323456", "0", "10");
		String[] Da3=ma.addInformationAcount("423456", "0", "10");
		
		ma.addAccount(ETypeAccount.CURRENT, Ca1);
		ma.addAccount(ETypeAccount.CURRENT, Ca2);
		ma.addAccount(ETypeAccount.CURRENT, Ca3);
		
		ma.addAccount(ETypeAccount.DEPOSIT, Da1);
		ma.addAccount(ETypeAccount.DEPOSIT, Da2);
		ma.addAccount(ETypeAccount.DEPOSIT, Da3);
		
		assertTrue(ma.addCheckBook("123456", "123456", "123456"));
		
		
		 
		
	}

	@Test
	void testGetAverageAccounts() throws Exception {
		ManagementAccount ma = new ManagementAccount();
		String[] Ca1=ma.addInformationAcount("123456", "0", "10");
		String[] Ca2=ma.addInformationAcount("123457", "1", "10");
		String[] Ca3=ma.addInformationAcount("123458", "2", "10");
		String[] Da1=ma.addInformationAcount("223456", "0", "10");
		String[] Da2=ma.addInformationAcount("323456", "0", "10");
		String[] Da3=ma.addInformationAcount("423456", "0", "10");
		
		ma.addAccount(ETypeAccount.CURRENT, Ca1);
		ma.addAccount(ETypeAccount.CURRENT, Ca2);
		ma.addAccount(ETypeAccount.CURRENT, Ca3);
		
		ma.addAccount(ETypeAccount.DEPOSIT, Da1);
		ma.addAccount(ETypeAccount.DEPOSIT, Da2);
		ma.addAccount(ETypeAccount.DEPOSIT, Da3);
		
		assertEquals(0.5, ma.getAverageAccounts());
		
		
	}

}
