package lotto.domain

import lotto.global.LottoValidator

const val LOTTO_PRICE = 1000
const val MAX_LOTTO_LENGTH = 6

data class Lotto(
    val value: List<LottoNumber>,
) {
    init {
        LottoValidator.requireValidLotto(value)
    }

    fun contains(element: LottoNumber): Boolean = value.contains(element)
}
