package lotto.entity

class Lottos(val value: List<Lotto>) {

    fun determineLottosResult(winLotto: WinLotto): WinStatistics {
        return WinStatistics(
            value.map {
                it.determineRank(it.determineCountOfMatch(winLotto.winNumber), it.determineMatchBonus(winLotto.bonus))
            }
        )
    }
}
