package lotto.domain.service

import lotto.constants.LottoConstants
import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount

class LottoMachine {
    fun generate(purchaseAmount: PurchaseAmount): List<Lotto> {
        val quantity = purchaseAmount.getPurchaseQuantity()
        return List(quantity) { generateLotto() }
    }

    private fun generateLotto(): Lotto {
        val lottoNumbers =
            (LottoConstants.MINIMUM_LOTTO_NUMBER..LottoConstants.MAXIMUM_LOTTO_NUMBER)
                .shuffled()
                .take(LottoConstants.NUMBER_OF_LOTTO_NUMBERS)
                .sorted()
                .map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }
}
