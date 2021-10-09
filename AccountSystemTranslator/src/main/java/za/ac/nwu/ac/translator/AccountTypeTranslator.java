package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;

import java.time.LocalDate;
import java.util.List;

public interface AccountTypeTranslator {
    List<AccountTypeDto> getAllAccountTypes();
    AccountTypeDto create(AccountTypeDto accountType);

    AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic);

    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);

    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);

    void someMethod();

    AccountTypeDto deleteAccountType(String mnemonic);

    AccountTypeDto updateAccountType(String mnemonic, String newAccountTypeName, LocalDate newCreationDate);
}
