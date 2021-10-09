package za.ac.nwu.ac.translator.impl;

import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.repo.persistence.AccountTransactionDetailsRepository;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;

public class AccountTransactionDetailsTranslatorImpl implements AccountTransactionDetailsTranslator {
    private AccountTransactionDetailsTranslator repo;

    public AccountTransactionDetailsTranslatorImpl(AccountTransactionDetailsRepository accountTransactionDetailsRepository) {
        this.repo = (AccountTransactionDetailsTranslator) accountTransactionDetailsRepository;
    }

    @Override
    public AccountTransactionDetails save(AccountTransactionDetails accountTransactionDetails){
        try{
            return repo.save(accountTransactionDetails);
        }catch (Exception e){
            throw new RuntimeException("Unable to save to the DB", e);
        }

    }
}
