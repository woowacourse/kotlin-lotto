package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import lotto.model.LottoNumber.Companion.LOTTO_NUMBER_MAX_RANGE
import lotto.model.LottoNumber.Companion.LOTTO_NUMBER_MIN_RANGE

class LottoMachine {
    fun getLottos(lottoQuantity: Int): Lottos {
        val lottos = List(lottoQuantity) { Lotto.from(getLottoNumbers()) }
        return Lottos(lottos)
    }

    private fun getLottoNumbers(): List<Int> {
        val shuffledNumbers = (LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE).shuffled()
        val selectedNumbers = shuffledNumbers.take(LOTTO_NUMBER_SIZE)

        return selectedNumbers.sorted()
    }
}
