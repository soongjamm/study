package etc.first_class_collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoService {
    public static final int LOTTO_NUMBER_SIZE = 6;

    public void createLottoNumber() {
        List<Long> lottoNumbers = createNonDuplicateNumber();
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
    }

    private void validateDuplicate(List<Long> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개만 가능합니다.");
        }
    }

    private void validateSize(List<Long> lottoNumbers) {
        Set<Long> nonDuplicateNumbers = new HashSet<>(lottoNumbers);
        if (nonDuplicateNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호들은 중복될 수 없습니다.");
        }
    }

    private List<Long> createNonDuplicateNumber() {
        return new ArrayList<>();
    }
}
