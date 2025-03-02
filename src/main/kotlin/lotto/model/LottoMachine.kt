package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_MAX_RANGE
import lotto.model.Lotto.Companion.LOTTO_NUMBER_MIN_RANGE
import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE

class LottoMachine(
    private val lottoQuantity: LottoQuantity,
) {
    fun generateActiveLottos(): List<Lotto> {
        val activeLottoQuantity = lottoQuantity.getActiveLottoQuantity()
        return List<Lotto>(activeLottoQuantity) { Lotto(getLottoNumbers().map { LottoNumber((it)) }.toSet()) }
    }

    private fun getLottoNumbers(): List<Int> {
        val shuffledLottoNumbers = (LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE).shuffled()
        val selectedLottoNumbers = shuffledLottoNumbers.take(LOTTO_NUMBER_SIZE)

        return selectedLottoNumbers.sorted()
    }
}
