package domain

import constants.LottoConstants
import domain.value.LottoNumber

class LottoMachine(
    private val purchaseAmount: Int,
) {
    private lateinit var lottos: Lottos

    init {
        initializeLottos()
    }

    fun generateLotto(): Lotto {
        val lottoNumbers =
            (LottoConstants.LOTTO_RANGE)
                .shuffled()
                .subList(0, LottoConstants.NUMBER_OF_LOTTO_NUMBERS)
                .map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }

    private fun initializeLottos() {
        val lottoCount = purchaseAmount / LottoConstants.LOTTO_PRICE
        lottos = Lottos(List(lottoCount) { generateLotto() })
    }
}
