package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.util.List;

public interface FetchAccountTypeFLow {
    List<AccountTypeDto> getAllAccountTypes();

    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);

    AccountType getAccountTypeDbEntityByMnemonic(String accountTypeMnemonic);
}
