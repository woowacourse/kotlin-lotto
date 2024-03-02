import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumberGenerator

class TestLottoNumberGenerator : LottoNumberGenerator {
    override fun generateNumbers(): Lotto {
        // 테스트를 위한 고정된 번호 세트 반환
        return Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }.toSet())
    }
}
