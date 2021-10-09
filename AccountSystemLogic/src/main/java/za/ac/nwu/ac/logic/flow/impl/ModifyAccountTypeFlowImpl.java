package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFLow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFLow {
    private final AccountTypeTranslator accountTypeTranslator;

    @Autowired
    public ModifyAccountTypeFlowImpl (AccountTypeTranslator accountTypeTranslator){
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public AccountTypeDto deleteAccountType(String mnemonic) {
        return accountTypeTranslator.deleteAccountType(mnemonic);
    }

    @Override
    public AccountTypeDto updateAccountType(String mnemonic, String newAccountTypeName, LocalDate newCreationDate) {
        return accountTypeTranslator.updateAccountType(mnemonic, newAccountTypeName, newCreationDate);
    }
}
