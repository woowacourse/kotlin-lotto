package lotto.model

import lotto.service.LottoNumberGenerator
import lotto.service.RandomLottoNumberGenerator

class Ticket(
    val purchasePrice: Int,
    lottoPrice: Int,
) {
    val amount = purchasePrice / lottoPrice

    fun issueLottos(lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator()): List<Lotto> {
        return List(amount) { Lotto(lottoNumberGenerator.generate()) }
    }

    init {
        require(purchasePrice > MINIMUM_PRICE && lottoPrice > MINIMUM_PRICE) {
            INVALID_PRICE
        }

        require(purchasePrice % lottoPrice == 0) {
            INVALID_PURCHASE.format(lottoPrice)
        }
    }

    companion object {
        private const val MINIMUM_PRICE = 0

        private const val INVALID_PRICE = "양수만 입력 가능합니다."
        private const val INVALID_PURCHASE = "%s 단위로 구매할 수 있습니다."
    }
}
