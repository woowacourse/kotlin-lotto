package lotto.domain

import lotto.global.Message

const val MAX_LOTTO_NUMBER = 45
const val MIN_LOTTO_NUMBER = 1

class LottoNumber private constructor(
    val value: Int,
) {
    companion object {
        private val pool = (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).map { LottoNumber(it) }.associateBy { it.value }

        fun of(value: Int): LottoNumber = pool[value] ?: throw IllegalArgumentException(Message.ERR_NOT_IN_RANGE.msg)
    }

    override fun toString(): String = value.toString()
}
