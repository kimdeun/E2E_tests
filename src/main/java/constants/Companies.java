package constants;

public enum Companies {
    TEST_COMPANY_FOR_AUTO_TESTS("TestCompanyForAutoTests"),
    ALPACA("Alpaca CO"),
    RED_FLAG("Red Flag Cargo Security Systems");
    private String company;

    Companies(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
}
