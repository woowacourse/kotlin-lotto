package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import lotto.model.LottoNumber.Companion.ALL_LOTTO_NUMBERS

class LottoMachine {
    fun getTotalLottos(
        manualLottos: List<Lotto>,
        autoQuantity: Int,
    ): List<Lotto> = manualLottos.plus(getAutoLottos(autoQuantity))

    private fun getAutoLottos(quantity: Int): List<Lotto> {
        val lottos = List(quantity) { Lotto.from(getLottoNumbers()) }
        return lottos
    }

    private fun getLottoNumbers(): List<Int> =
        ALL_LOTTO_NUMBERS
            .shuffled()
            .take(LOTTO_NUMBER_SIZE)
            .sorted()
}
