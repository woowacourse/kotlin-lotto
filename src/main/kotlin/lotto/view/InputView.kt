package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoMoney
import lotto.domain.LottoNumber

object InputView {
    fun readInputMoney(): LottoMoney? {
        val input = readln()
        return LottoMoney(input.toIntOrNull() ?: return null)
    }

    fun readInputWinningLotto(): Lotto? {
        val lottoNumbers = readln().split(",").map {
            LottoNumber.from(it.toIntOrNull() ?: return null)
        }
        return Lotto(lottoNumbers)
    }

    fun readInputBonusNumber(): LottoNumber? {
        return LottoNumber.from(readln().toIntOrNull() ?: return null)
    }

    fun readInputManualLottoCount(): Int? {
        return readln().toIntOrNull()
    }

    fun readInputManualLotto(count: Int): List<Lotto>? {
        val manualLotto = mutableListOf<Lotto>()
        repeat(count) {
            val input = readln()
            val lottoNumbers = input.split(",").map { LottoNumber.from(it.trim().toIntOrNull() ?: return null) }
            manualLotto.add(Lotto(lottoNumbers))
        }
        return manualLotto
    }
}
