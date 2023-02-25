package lotto.model

import lotto.view.ERROR_OUT_OF_LOTTO_NUMBER

class ManualLotto(
    val numberOfLotto: Int,
    val lotto: MutableList<Lotto> = mutableListOf(),
) {

    fun validateNumberOfLotto(totalNumber: Int): ManualLotto {
        require(totalNumber >= numberOfLotto) { ERROR_OUT_OF_LOTTO_NUMBER }
        return this
    }

    fun add(input: Lotto) {
        lotto.add(input)
    }
}
