package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "DEMO_ACCOUNT_TYPE", schema = "CMPG323")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = -1420294317019175746L;

    private Long transactionID;
    private AccountType accountType;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;

    private AccountTransactionDetails details;

    public AccountTransaction(Long transactionID, AccountType accountType, Long memberId, Long amount, LocalDate transactionDate) {
    }

    public AccountTransaction(Long transactionID, AccountType accountType, Long memberId, Long amount, LocalDate transactionDate, AccountTransactionDetails details) {
        this.transactionID = transactionID;
        this.accountType = accountType;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.details = details;
    }



    @Id
    @SequenceGenerator(name = "DEMO_ACCOUNT_TYPE_SEQUENCE", sequenceName = "CMPG323.DEMO_ACCOUNT_TYPE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEMO_ACCOUNT_TYPE_SEQUENCE")
    @Column(name = "ACCOUNT_TX_ID")
    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    public AccountType getAccountType(){
        return accountType;
    }

    public void setAccountType(AccountType accountType){
        this.accountType = accountType;
    }

    @Column(name = "MEMBER_ID")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "AMOUNT")
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Column(name = "TX_DATE")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @OneToOne(targetEntity = AccountTransactionDetails.class, fetch = FetchType.LAZY, mappedBy = "accountTransaction"/*, orphanRemoval = true, cascade = CascadeType.PERSIST*/)
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
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(transactionID, that.transactionID) && Objects.equals(accountType, that.accountType) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, accountType, memberId, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionID=" + transactionID +
                ", accountType=" + accountType +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
