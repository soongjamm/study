package etc.enums.enumExample;

public interface ContractRepository {
    Contract findByCommissionType(String commissionType);
    Contract findByCommissionCutting(String commissionCutting);
}
