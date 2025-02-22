package view

import domain.model.Lotto
import domain.model.Rank
import util.joinToLineBreak

object Messenger {
    fun makePurchaseLottoMessage(purchaseLotto: List<Lotto>): String {
        return purchaseLotto.map { it.numbers.map { it.value } }.joinToLineBreak()
    }
}
