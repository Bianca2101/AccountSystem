package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFLow;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

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
        if (LOGGER.isInfoEnabled()){
            String outputForLogging = null;
            if((null != accountTransactionDto) && (null != accountTransactionDto.getDetails())){
                outputForLogging = accountTransactionDto.getDetails().toString();

            }
            LOGGER.info("The input object is {} and the Details is {}", accountTransactionDto, outputForLogging);
        }
        accountTransactionDto.setTransactionID(null);

        AccountTypeDto accountType = fetchAccountTypeFLow.getAccountTypeByMnemonic(accountTransactionDto.getAccountTypeMnemonic());

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Got AccountType for {} and the AccountTypeID is {}", accountTransactionDto.getAccountTypeMnemonic(),accountType.getAccountTypeId());
        }

        AccountTransaction accountTransaction = accountTransactionDto.buildAccountTransaction(accountType);

        AccountTransaction createdAccountTransaction = accountTransactionTranslator.save(accountTransaction);

        if(null != accountTransactionDto.getDetails()){
            AccountTransactionDetails accountTransactionDetails = accountTransactionDto.getDetails().buildAccountTransactionDetails(createdAccountTransaction);
            createdAccountTransaction.setDetails(accountTransactionDetails);
            accountTransactionDetailsTranslator.save(createdAccountTransaction.getDetails());
        }
        AccountTransactionDto results = new AccountTransactionDto(createdAccountTransaction);
        LOGGER.info("The return object is {}", results);
        return results;
    }
}
