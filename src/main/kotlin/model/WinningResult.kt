package model

import entity.Ticket

class WinningResult(val stats: Map<Rank, Int>, val roi: Double) {
    companion object {
        private const val DECIMALS = 2
        private const val BASE_MULTIPLIER = 10

        fun of(
            ticket: Ticket,
            winningLotto: Lotto,
            bonus: Bonus,
        ): WinningResult {
            val stats = getStats(ticket, winningLotto, bonus)
            val roi = calculateROI(ticket, stats)

            return WinningResult(stats, roi)
        }

        fun Double.round(decimals: Int): Double {
            var multiplier = 1.0
            repeat(decimals) { multiplier *= BASE_MULTIPLIER }
            return kotlin.math.round(this * multiplier) / multiplier
        }

        private fun getStats(
            ticket: Ticket,
            winningLotto: Lotto,
            bonus: Bonus,
        ): Map<Rank, Int> {
            val ranks = mutableMapOf<Rank, Int>()

            ticket.userLotteries.forEach {
                val rank = Rank.valueOf(it.getCountOfMatch(winningLotto), it.hasBonus(bonus))
                ranks[rank] = (ranks[rank] ?: 0) + 1
            }
            return ranks
        }

        private fun calculateROI(
            ticket: Ticket,
            stats: Map<Rank, Int>,
        ): Double {
            var prize = 0.0

            stats.forEach {
                prize += (it.key.winningMoney * it.value)
            }

            return (prize / ticket.amount.money).round(DECIMALS)
        }
    }
}
