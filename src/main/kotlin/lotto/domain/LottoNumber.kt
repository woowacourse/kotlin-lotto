package lotto.domain

import lotto.global.Message

const val MAX_LOTTO_NUMBER = 45
const val MIN_LOTTO_NUMBER = 1

class LottoNumber private constructor(
    val value: Int,
) { // private 생성자로 외부 생성을 막음
    companion object {
        private val pool = (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).map { LottoNumber(it) }.associateBy { it.value }

        fun of(value: Int): LottoNumber = requireNotNull(pool[value]) { Message.ERR_NOT_IN_RANGE.msg }
    }

    override fun toString(): String = value.toString()
}
