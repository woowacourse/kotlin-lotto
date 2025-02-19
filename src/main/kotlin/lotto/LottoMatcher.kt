package lotto

class LottoMatcher {
    fun calculateRank(
        userLotto: Lotto,
        winLotto: Lotto,
        bonusNumber: LottoNumber,
    ): Rank {
        var matchCount = 0
        var bonusMatch = false
        for (lottoNumber in userLotto.numbers) {
            if (winLotto.numbers.contains(lottoNumber)) matchCount++
        }
        if (userLotto.numbers.contains(bonusNumber)) bonusMatch = true
        return Rank.valueOf(matchCount, bonusMatch)
    }
}
