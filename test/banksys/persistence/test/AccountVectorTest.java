package banksys.persistence.test;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.persistence.AccountVector;
import banksys.persistence.exception.AbstractAccountEmptyException;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountVectorTest {

	
	/*Cenario de Teste 01 - Criando vetor de contas com sucesso
	 * Dados de Entrada: Conta Ordinary
	 * Dados de Saida: Vetor de contas criado e populado*/
	@Test
	public void testPopulandoVectorContasNormal() throws AccountCreationException{
		AccountVector accountVector = new AccountVector();
		OrdinaryAccount account = new OrdinaryAccount("1234");
		
		int numberBefore = accountVector.numberOfAccounts(); 
		
		accountVector.create(account);
		
		int numberAfter = accountVector.numberOfAccounts(); 
		
		//verificando o tamanho do vector de contas antes e depois da insercao
		assertFalse(numberAfter == numberBefore);
	}
	
	/*Test Scenario 02: Check add accounts with number account equals 
	 * */
	
	@Test (expected=AccountCreationException.class)
	public void testPopulandoVectorContasComUmaJaExistente() throws AccountCreationException{
		AccountVector accountVector = new AccountVector();
		OrdinaryAccount accountA = new OrdinaryAccount("1234");
		OrdinaryAccount accountB = new OrdinaryAccount("1234");
		
		accountVector.create(accountA);
		accountVector.create(accountB);
	}
	
	/*Test Scenario 03: Check method mumberOfAccounts in number of account equals zero and one*/
	@Test
	public void testNumeroDeContas() throws AccountCreationException{
		AccountVector accountVector = new AccountVector();
		assertEquals(0, accountVector.numberOfAccounts());
		
		OrdinaryAccount accountA = new OrdinaryAccount("1234");
		accountVector.create(accountA);
		assertEquals(1, accountVector.numberOfAccounts());
	}
	
	/*Test Scenario 04: Check method delete account with account doesn't exist*/
	@Test (expected=AccountDeletionException.class)
	public void testRemoverContaQaundoNaoExiste() throws AccountDeletionException{
		AccountVector accountVector = new AccountVector();
		accountVector.delete("1234");
	}
	
	/*Test Scenario 05: Check method list account with vector empty*/
	@Test (expected=AbstractAccountEmptyException.class)
	public void testListarVetorContasVazio() throws AbstractAccountEmptyException{
		AccountVector accountVector = new AccountVector();
		accountVector.list();
	}
	
	//Test Scenario 06: Check method list account with vector size equals one
	@Test
	public void testListarVectorContasPreenchido() throws AccountCreationException, AbstractAccountEmptyException{
		AccountVector accountVector = new AccountVector();
		OrdinaryAccount accountA = new OrdinaryAccount("1234");
		
		accountVector.create(accountA);
		
		assertEquals(1, accountVector.list().length);
	}
	
	// Test Scenario 07: Check method delete account existent
	@Test
	public void testDeleteConta () throws AccountDeletionException, AccountCreationException{
		AccountVector accountVector = new AccountVector();
		OrdinaryAccount accountA = new OrdinaryAccount("1234");
		
		accountVector.create(accountA);
		
		int numberBeforeDeleted = accountVector.numberOfAccounts();
		
		//delete
		accountVector.delete("1234");
		
		int numberAfterDeleted = accountVector.numberOfAccounts();
		
		assertFalse(numberBeforeDeleted == numberAfterDeleted);
		
	}
	
	// Test Scenario 08:  check method Retrieve account existent
	@Test
	public void testRetriveAccountExcistent() throws AccountCreationException, AccountNotFoundException{
		AccountVector accountVector = new AccountVector();
		OrdinaryAccount accountA = new OrdinaryAccount("1234");
		
		accountVector.create(accountA);
		
		OrdinaryAccount accountB  =  (OrdinaryAccount) accountVector.retrieve("1234");

		assertEquals("1234", accountB.getNumber());
	}
	

	// Test Scenario 09:  check method Retrieve account not existent
	@Test (expected=AccountNotFoundException.class)
	public void testRetriveAccountNotExcistent() throws AccountNotFoundException{
		AccountVector accountVector = new AccountVector();
		accountVector.retrieve("1234");
	}
	
}


