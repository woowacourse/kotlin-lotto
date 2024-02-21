import lotto.model.NumberGenerator
import model.LottoNumber

class TestNumberGenerator : NumberGenerator {
    override fun generate() = listOf(LottoNumber(1),LottoNumber(2),LottoNumber(3),LottoNumber(4),LottoNumber(5),LottoNumber(6))
}
