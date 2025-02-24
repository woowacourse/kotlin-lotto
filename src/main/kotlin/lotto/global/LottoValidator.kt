package lotto.global

import lotto.domain.LottoNumber
import lotto.domain.MAX_LOTTO_LENGTH
import lotto.domain.MAX_LOTTO_NUMBER
import lotto.domain.MIN_LOTTO_NUMBER
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
                }.map { number -> LottoNumber.of(number) }
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
        require(rawInput in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) { Message.ERR_NOT_IN_RANGE.msg }
        return input
    }
}
