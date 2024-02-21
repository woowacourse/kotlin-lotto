package model

class LottoDrawingMachine {

    fun countRank(lottoTickets: List<Lotto>, winningLotto: Lotto, bonusNumber: LottoNumber): LottoDrawingResult {
        val result = mutableListOf<Rank>()
        lottoTickets.forEach { targetLotto ->
            result.add(getRank(targetLotto, winningLotto, bonusNumber))
        }
        return LottoDrawingResult(result.groupingBy { it }.eachCount())
    }

    private fun getRank(targetLotto: Lotto, winningLotto: Lotto, bonusNumber: LottoNumber): Rank {
        val countOfMatch = matchCount(targetLotto, winningLotto)
        val matchBonus = bonusCount(targetLotto, bonusNumber)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun matchCount(targetLotto: Lotto, winningLotto: Lotto): Int {
        return (targetLotto.numbers.intersect(winningLotto.numbers.toSet())).size
    }

    private fun bonusCount(targetLotto: Lotto, bonusNumber: LottoNumber): Boolean {
        return targetLotto.numbers.contains(bonusNumber)
    }
}
