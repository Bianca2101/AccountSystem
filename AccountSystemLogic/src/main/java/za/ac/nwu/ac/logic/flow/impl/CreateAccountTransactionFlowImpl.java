package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFLow;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

@Component
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {



    private final AccountTransactionTranslator accountTransactionTranslator;
    private final AccountTransactionDetailsTranslator accountTransactionDetailsTranslator;
    private final FetchAccountTypeFLow fetchAccountTypeFLow;

    public CreateAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator, AccountTransactionDetailsTranslator accountTransactionDetailsTranslator, FetchAccountTypeFLow fetchAccountTypeFLow) {
        this.accountTransactionTranslator = accountTransactionTranslator;
        this.accountTransactionDetailsTranslator = accountTransactionDetailsTranslator;
        this.fetchAccountTypeFLow = fetchAccountTypeFLow;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto){
        accountTransactionDto.setTransactionID(null);

        AccountTypeDto accountType = fetchAccountTypeFLow.getAccountTypeByMnemonic(accountTransactionDto.getAccountTypeMnemonic());
        AccountTransaction accountTransaction = accountTransactionDto.buildAccountTransaction(accountType);

        AccountTransaction createdAccountTransaction = accountTransactionTranslator.save(accountTransaction);

        if(null != accountTransactionDto.getDetails()){
            AccountTransactionDetails accountTransactionDetails = accountTransactionDto.getDetails().buildAccountTransactionDetails(createdAccountTransaction);
            createdAccountTransaction.setDetails(accountTransactionDetails);
            accountTransactionDetailsTranslator.save(createdAccountTransaction.getDetails());
        }
        return new AccountTransactionDto(createdAccountTransaction);
    }
}
