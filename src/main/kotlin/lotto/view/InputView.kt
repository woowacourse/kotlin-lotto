package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoNumber

object InputView {
    private const val LOTTO_NUMBERS_DELIMITER = ","
    private const val ERROR_MESSAGE_INVALID_INPUT_STATE = "정상적으로 입력되지 않았습니다."
    private const val ERROR_MESSAGE_INPUT_NOT_A_NUMBER = "숫자를 입력해주세요."
    private const val ERROR_MESSAGE_INVALID_WINNING_NUMBERS = "양식에 맞게 입력해주세요. (1, 2, 3, 4, 5, 6)"

    fun readPrice(): Int {
        val input: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val price: Int = input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return price
    }

    fun readWinningNumbers(): Lotto {
        val lottoInput: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val wonLotto = Lotto(lottoInput.toLottoNumbers())
        return wonLotto
    }

    private fun String.toLottoNumbers(): Set<LottoNumber> =
        split(LOTTO_NUMBERS_DELIMITER)
            .map { number: String ->
                val number: Int =
                    number.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_WINNING_NUMBERS)
                LottoNumber(number)
            }.toSet()

    fun readBonusNumber(): Int {
        val bonusInput: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val bonusNumber: Int =
            bonusInput.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return bonusNumber
    }
}
