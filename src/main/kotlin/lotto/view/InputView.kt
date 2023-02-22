package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoMoney
import lotto.domain.LottoNumber
import lotto.exception.Validator

object InputView {
    fun readInputMoney(): LottoMoney {
        val input = readln()
        Validator.checkInputMoney(input)
        return LottoMoney(input.toInt())
    }

    fun readInputWinningLotto(): Lotto {
        val input = readln()
        return Lotto(input.split(",").map { LottoNumber.from(it.toInt()) })
    }

    fun readInputBonusNumber(): LottoNumber {
        val input = readln()
        Validator.checkInputBonusNumber(input)
        return LottoNumber.from(input.toInt())
    }

    fun readInputManualCount(totalCount: Int): Int {
        val input = readln()
        Validator.checkInputManualCount(input, totalCount)
        return input.toInt()
    }

    fun readInputManualLotto(count: Int): List<Lotto> {
        val manualLotto = mutableListOf<Lotto>()
        repeat(count) {
            val input = readln()
            Validator.checkInputManualLottoNumbers(input)
            manualLotto.add(makeInputToLotto(input))
        }
        return manualLotto
    }

    private fun makeInputToLotto(input: String): Lotto {
        return Lotto(input.split(",").map { LottoNumber.from(it.trim().toInt()) })
    }
}
