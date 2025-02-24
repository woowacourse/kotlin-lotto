package lotto.domain

import lotto.global.Config.MAX_RANDOM_NUM
import lotto.global.Config.MIN_RANDOM_NUM
import lotto.global.Message

data class LottoNumber(
    val value: Int,
) {
    init {
        require(value in MIN_RANDOM_NUM..MAX_RANDOM_NUM) { Message.ERR_NOT_IN_RANGE.msg }
    }
}
