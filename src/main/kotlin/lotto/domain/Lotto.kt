package lotto.domain

import lotto.global.LottoValidator.requireValidLotto

const val LOTTO_PRICE = 1000
const val MAX_LOTTO_LENGTH = 6

data class Lotto(
    val value: List<LottoNumber>,
) {
    init {
        requireValidLotto(value)
    }

    fun getCountOfMatchWith(contrast: Lotto): Int = value.count { it in contrast.value }

    fun contains(element: Int): Boolean = value.contains(LottoNumber.of(element))
}
