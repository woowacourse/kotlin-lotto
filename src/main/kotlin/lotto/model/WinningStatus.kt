package lotto.model

import lotto.model.LottoMachine.TICKET_PRICE
import lotto.util.WinningRank

data class WinningStatus(val resultStatus: Map<WinningRank, Int>) {
    fun getEarningRate(): Double {
        val total: Long = resultStatus.entries.sumOf { it.key.winningMoney * it.value }
        return (total / (resultStatus.values.sum() * TICKET_PRICE).toDouble())
    }
}
