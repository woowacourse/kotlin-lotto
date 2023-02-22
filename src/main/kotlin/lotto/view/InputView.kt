package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.exception.Validator

object InputView {
    fun readInputMoney(): Int? {
        val input = readln()
        return input.toIntOrNull()
    }

    fun readInputManualLottoCount(count: Int): Int {
        val input = readln()
        Validator.checkInputManualLottoCount(input, count)
        return input.toInt()
    }

    fun readManualLottoNumbers(count: Int): List<Lotto> {
        val lotto = mutableListOf<Lotto>()
        for (i in 0 until count) {
            val input = readln()
            lotto.add(Lotto(input.split(",").map { LottoNumber(it.toInt()) }))
        }
        return lotto
    }

    fun readInputWinningLotto(): Lotto {
        val input = readln()
        return Lotto(input.split(",").map { LottoNumber(it.toInt()) })
    }

    fun readInputBonusNumber(): LottoNumber {
        val input = readln()
        Validator.checkInputBonusNumber(input)
        return LottoNumber(input.toInt())
    }
}
