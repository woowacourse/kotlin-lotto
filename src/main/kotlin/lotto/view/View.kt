package lotto.view

import lotto.domain.Lotto
import lotto.domain.Lottos
import lotto.domain.WinningLotto

object View {
    fun readPriceInput() {
        println(MESSAGE_ENTER_PRICE)
        val input: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val price: Int = input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        val lottoCount: Int = price / Lotto.LOTTO_PRICE

        println("${lottoCount}개를 구매했습니다.")
        val lottoNumbers: List<List<Int>> = List(lottoCount) { makeRandomNumbers(Lotto.LOTTO_SIZE) }
        val lottos: Lottos =
            Lottos.buy(
                price = price,
                *(
                    lottoNumbers.map { lottoNumber: List<Int> -> Lotto(*lottoNumber.toIntArray()) }
                ).toTypedArray(),
            )
        lottos.lottos.forEach { lotto: Lotto ->
            println(lotto.numbers.sorted())
        }
    }

    fun requestWinningLotto() {
    }

    fun readWinningLotto(): WinningLotto {
        println(MESSAGE_ENTER_WINNING_NUMBERS)
        val lottoInput: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val wonLotto: Lotto =
            Lotto(
                *(
                    lottoInput.split(LOTTO_NUMBERS_DELIMITER).map {
                        it.trim().toIntOrNull() ?: throw IllegalArgumentException(MESSAGE_INVALID_WINNING_NUMBERS)
                    }
                ).toIntArray(),
            )
        println(MESSAGE_ENTER_BONUS_NUBMER)
        val bonusInput: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val bonusNumber: Int =
            bonusInput.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return WinningLotto(wonLotto, bonusNumber)
    }

    private fun makeRandomNumbers(size: Int): List<Int> = (Lotto.LOTTO_NUMBER_MIN..Lotto.LOTTO_NUMBER_MAX).shuffled().subList(0, size)

    private const val LOTTO_NUMBERS_DELIMITER = ","
    private const val MESSAGE_ENTER_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_BONUS_NUBMER = "보너스 볼을 입력해 주세요."
    private const val MESSAGE_ENTER_PRICE = "구입금액을 입력해 주세요."
    private const val MESSAGE_INVALID_WINNING_NUMBERS = "양식에 맞게 입력해주세요. (1, 2, 3, 4, 5, 6)"
    private const val ERROR_MESSAGE_INVALID_INPUT_STATE = "정상적으로 입력되지 않았습니다."
    private const val ERROR_MESSAGE_INPUT_NOT_A_NUMBER = "숫자를 입력해주세요."
}
