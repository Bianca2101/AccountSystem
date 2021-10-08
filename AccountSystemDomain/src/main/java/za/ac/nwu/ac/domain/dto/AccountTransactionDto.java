package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.time.LocalDate;
import java.util.Objects;

public class AccountTransactionDto {

    private static final long serialVersionUID = -6731456901152363824L;
    private Long transactionID;
    private String accountTypeMnemonic;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;
    private AccountTransactionDetails details;

    public AccountTransactionDto() {
    }

    public AccountTransactionDto(Long transactionID, String accountTypeMnemonic, Long memberId, Long amount, LocalDate transactionDate) {
        this.transactionID = transactionID;
        this.accountTypeMnemonic = accountTypeMnemonic;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;

    }

    public AccountTransactionDto(Long transactionID, String accountTypeMnemonic, Long memberId, Long amount, LocalDate transactionDate, AccountTransactionDetails details) {
        this.transactionID = transactionID;
        this.accountTypeMnemonic = accountTypeMnemonic;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.details = details;
    }

    public AccountTransactionDto(AccountTransaction accountTransaction) {
        this.transactionID = accountTransaction.getTransactionID();
        this.accountTypeMnemonic = accountTransaction.getAccountType().getMnemonic();
        this.memberId = accountTransaction.getMemberId();
        this.amount = accountTransaction.getAmount();
        this.transactionDate = accountTransaction.getTransactionDate();
        this.details = accountTransaction.getDetails();
        if(null != accountTransaction.getDetails()){
            this.details = new AccountTransactionDetailsDto(accountTransaction.getDetails());
        }
    }

    @JsonIgnore
    public AccountTransaction buildAccountTransaction(AccountType accountType){
        return new AccountTransaction(this.getTransactionID(), accountType, this.getMemberId(), this.getAmount(), this.getTransactionDate());
    }


    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public String getAccountTypeMnemonic() {
        return accountTypeMnemonic;
    }

    public void setAccountTypeMnemonic(String accountTypeMnemonic) {
        this.accountTypeMnemonic = accountTypeMnemonic;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public AccountTransactionDetails getDetails() {
        return details;
    }

    public void setDetails(AccountTransactionDetails details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return Objects.equals(transactionID, that.transactionID) && Objects.equals(accountTypeMnemonic, that.accountTypeMnemonic) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, accountTypeMnemonic, memberId, amount, transactionDate, details);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "transactionID=" + transactionID +
                ", accountTypeMnemonic='" + accountTypeMnemonic + '\'' +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", details=" + details +
                '}';
    }
}
