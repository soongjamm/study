package etc.first_class_collection;

import java.util.ArrayList;
import java.util.List;

public class LottoService2 {

    public void createLottoNumber() {
        LottoTicket lottoTicket = new LottoTicket(createNonDuplicateNumbers());
    }

    private List<Long> createNonDuplicateNumbers() {
        return new ArrayList<>();
    }
}
