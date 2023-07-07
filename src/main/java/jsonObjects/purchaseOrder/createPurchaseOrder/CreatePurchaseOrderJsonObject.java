package jsonObjects.purchaseOrder.createPurchaseOrder;

import java.util.List;

public class CreatePurchaseOrderJsonObject {
    private Buyer buyer;
    private Code code;
    private Company company;
    private List<ExcludedSymbols> excludedSymbols;
    private String name;

    public CreatePurchaseOrderJsonObject(Buyer buyer, Code code, Company company, List<ExcludedSymbols> excludedSimbolsList, String name) {
        this.buyer = buyer;
        this.code = code;
        this.company = company;
        this.excludedSymbols = excludedSimbolsList;
        this.name = name;
    }

    public CreatePurchaseOrderJsonObject() {
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Code getCode() {
        return code;
    }

    public Company getCompany() {
        return company;
    }

    public List<ExcludedSymbols> getExcludedSymbols() {
        return excludedSymbols;
    }

    public String getName() {
        return name;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setExcludedSymbols(List<ExcludedSymbols> excludedSymbols) {
        this.excludedSymbols = excludedSymbols;
    }

    public void setName(String name) {
        this.name = name;
    }
}
