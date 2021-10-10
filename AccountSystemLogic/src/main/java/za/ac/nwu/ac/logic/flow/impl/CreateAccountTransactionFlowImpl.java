package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFLow;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private final AccountTransactionTranslator accountTransactionTranslator;

    private final FetchAccountTypeFLow fetchAccountTypeFLow;

    public CreateAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator, FetchAccountTypeFLow fetchAccountTypeFLow) {
        this.accountTransactionTranslator = accountTransactionTranslator;
        this.fetchAccountTypeFLow = fetchAccountTypeFLow;
    }
    

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto){

        LOGGER.info("Input object was {}", accountTransactionDto);
        accountTransactionDto.setTransactionID(null);

        AccountType accountType = fetchAccountTypeFLow.getAccountTypeDbEntityByMnemonic(accountTransactionDto.getAccountTypeMnemonic());

        LOGGER.info("The account type object is {}", accountType);
        /*if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Got AccountType for {} and the AccountTypeID is {}", accountTransactionDto.getAccountTypeMnemonic(),accountType.getAccountTypeId());
        }*/

        AccountTransaction accountTransaction = accountTransactionDto.buildAccountTransaction(accountType);

        LOGGER.info("The account transaction object is {}", accountTransaction);
        AccountTransaction createdAccountTransaction = accountTransactionTranslator.save(accountTransaction);

        LOGGER.info("The created account transaction object is {}", createdAccountTransaction);

        AccountTransactionDto results = new AccountTransactionDto(createdAccountTransaction);
        LOGGER.info("The return object is {}", results);
        return results;
    }
}
