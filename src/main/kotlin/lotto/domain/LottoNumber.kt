package lotto.domain

import lotto.global.Config.MAX_RANDOM_NUM
import lotto.global.Config.MIN_RANDOM_NUM
import lotto.global.Message

class LottoNumber private constructor(
    val value: Int,
) { // private 생성자로 외부 생성을 막음
    companion object {
        private val pool = (MIN_RANDOM_NUM..MAX_RANDOM_NUM).map { LottoNumber(it) }.associateBy { it.value }

        fun of(value: Int): LottoNumber = requireNotNull(pool[value]) { Message.ERR_NOT_IN_RANGE.msg }
    }

    override fun toString(): String = value.toString()
}
