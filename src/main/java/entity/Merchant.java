package entity;

public class Merchant {
    private String documentNumber; //0
    private String account; //7
    private String accountDigit; //7
    private String bank; //hardcode = 1 (banco do brasil)
    private String bankBranch; //5
    private String bankBranchAccountDigit;
    private String bankBranchDigit; //6
    private String documentType; //hardcode = 2 (cnpj)
    private String merchantName; //não é usado para validar na accesstage

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountDigit() {
        return accountDigit;
    }

    public void setAccountDigit(String accountDigit) {
        this.accountDigit = accountDigit;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankBranchAccountDigit() {
        return bankBranchAccountDigit;
    }

    public void setBankBranchAccountDigit(String bankBranchAccountDigit) {
        this.bankBranchAccountDigit = bankBranchAccountDigit;
    }

    public String getBankBranchDigit() {
        return bankBranchDigit;
    }

    public void setBankBranchDigit(String bankBranchDigit) {
        this.bankBranchDigit = bankBranchDigit;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}