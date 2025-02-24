package lotto.global

import lotto.domain.LottoNumber
import lotto.global.Config.MAX_LOTTO_LENGTH
import lotto.global.Config.MAX_RANDOM_NUM
import lotto.global.Config.MIN_RANDOM_NUM
import java.lang.IllegalArgumentException

object LottoValidator {
    fun requireLottoAmount(input: String): String {
        require(input.toIntOrNull() != null) { Message.ERR_INVALID_FORMAT.msg }
        return input
    }

    fun requireValidLotto(input: String): String {
        val winningLotto =
            input
                .split(",")
                .map { number ->
                    number.toIntOrNull() ?: throw IllegalArgumentException(Message.ERR_INVALID_FORMAT.msg)
                }.map { number -> LottoNumber(number) }
        requireValidLotto(winningLotto)
        return input
    }

    fun requireValidLotto(input: List<LottoNumber>): List<LottoNumber> {
        var winningLotto = input.toList()
        require(input.size == MAX_LOTTO_LENGTH) { Message.ERR_NOT_SIX_ELEMENTS.msg }
        winningLotto = winningLotto.distinct()
        require(winningLotto.size == MAX_LOTTO_LENGTH) { Message.ERR_ELEMENT_DUPLICATED.msg }
        return input
    }

    fun requireValidBonusNum(input: String): String {
        val rawInput = input.toIntOrNull() ?: throw IllegalArgumentException(Message.ERR_INVALID_FORMAT.msg)
        require(rawInput in MIN_RANDOM_NUM..MAX_RANDOM_NUM) { Message.ERR_NOT_IN_RANGE.msg }
        return input
    }
}
