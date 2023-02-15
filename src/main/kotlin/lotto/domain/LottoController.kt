package lotto.domain

import lotto.model.Lotto
import lotto.model.generator.LottoGenerator

class LottoController {

    fun getNumberOfLotto(money: Int): Int {
        return money / LOTTO_PRICE
    }

    fun generateLotto(number: Int): List<Lotto> {
        val lotto = mutableListOf<Lotto>()
        repeat(number) {
            lotto.add(Lotto(LottoGenerator().generate()))
        }
        return lotto
    }

    fun isDivided(money: Int): Boolean {
        return money % LOTTO_PRICE == 0
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}

fun String.isNumber() = this.chars().allMatch { Character.isDigit(it) }
