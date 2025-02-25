package lotto.domain

import lotto.global.Message

const val MAX_LOTTO_NUMBER = 45
const val MIN_LOTTO_NUMBER = 1

class LottoNumber private constructor(
    val value: Int,
) {
    companion object {
        private const val LOTTO_NUMBER_MIN = 1
        private const val LOTTO_NUMBER_MAX = 45
        private val pool = (LOTTO_NUMBER_MIN..LOTTO_NUMBER_MAX).map { LottoNumber(it) }.associateBy { it.value }

        fun of(value: Int): LottoNumber = pool[value] ?: throw IllegalArgumentException(Message.ERR_NOT_IN_RANGE.msg)
    }
}
