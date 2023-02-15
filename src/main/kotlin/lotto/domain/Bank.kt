package lotto.domain

import lotto.constant.Rank

class Bank {
    fun countMatchedMainLottoNumber(lotto: Lotto, winningLotto: WinningLotto): Int =
        lotto.lottoNumbers.count { lottoNumber -> lottoNumber.value in winningLotto.mainLottoNumbers.map { it.value } }

    fun checkMatchedBonusLottoNumber(lotto: Lotto, winningLotto: WinningLotto): Boolean =
        winningLotto.bonusLottoNumber.value in lotto.lottoNumbers.map { lottoNumber -> lottoNumber.value }

    fun getPrizeMoney(lotto: Lotto, winningLotto: WinningLotto): Int {
        val rank = Rank.convertToGrade(
            countMatchedMainLottoNumber(lotto, winningLotto),
            checkMatchedBonusLottoNumber(lotto, winningLotto),
        )
        return Rank.convertToPrizeMoney(rank)
    }
}
