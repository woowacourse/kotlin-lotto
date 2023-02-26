package lotto.model

import lotto.view.ERROR_OUT_OF_LOTTO_NUMBER

class ManualLottos(
    val numberOfLotto: Int,
    lotto: List<Lotto> = listOf(),
) {
    private val _lotto: MutableList<Lotto> = lotto.toMutableList()
    val lotto: List<Lotto>
        get() = _lotto.toList()

    fun validateNumberOfLotto(totalNumber: Int): ManualLottos {
        require(totalNumber >= numberOfLotto) { ERROR_OUT_OF_LOTTO_NUMBER }
        return this
    }

    fun add(lotto: Lotto) {
        _lotto.add(lotto)
    }
}
