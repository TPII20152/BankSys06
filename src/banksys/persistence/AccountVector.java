package banksys.persistence;

import java.util.Vector;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AbstractAccountEmptyException;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountVector implements IAccountRepository {

	private Vector<AbstractAccount> accounts = null;

	public AccountVector() {
		this.accounts = new Vector<AbstractAccount>();
	}

	public void delete(String number) throws AccountDeletionException {
		AbstractAccount account = this.findAccount(number);
		if (account != null) {
			this.accounts.remove(account);
		} else {
			throw new AccountDeletionException("OrdinaryAccount doesn't exist!", number);
		}
	}

	public void create(AbstractAccount account) throws AccountCreationException {
		if (this.findAccount(account.getNumber()) == null || accounts.size() == 0) {
			this.accounts.addElement(account);
		} else {
			throw new AccountCreationException("OrdinaryAccount alredy exist!", account.getNumber());
		}
	}

	public AbstractAccount[] list() throws AbstractAccountEmptyException {
		AbstractAccount[] list = null;
		if (this.accounts.size() > 0) {
			list = new AbstractAccount[this.accounts.size()];
			for (int i = 0; i < this.accounts.size(); i++) {
				list[i] = (AbstractAccount) this.accounts.elementAt(i);
			}
		}else {
			throw new AbstractAccountEmptyException("Vector of account is Empty");
		}
		return list;
	}

	public int numberOfAccounts() {
		return this.accounts.size();
	}

	public AbstractAccount retrieve(String number) throws AccountNotFoundException {
		AbstractAccount account = findAccount(number);
		if (account != null) {
			return account;
		} else {
			throw new AccountNotFoundException("OrdinaryAccount not found!", number);
		}
	}

	private AbstractAccount findAccount(String number) {

		if (this.accounts.size() >= 1) {
			for (int i = 0; i <= this.accounts.size(); i++) {
				AbstractAccount account = (AbstractAccount) this.accounts.elementAt(i);
	
				if (account.getNumber().equals(number)) {
					return account;
				}
			}
		}
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}
