package lotto.domain

class Bank {
    fun countMatchedMainLottoNumber(lotto: Lotto, winningLotto: WinningLotto): Int =
        lotto.lottoNumbers.count { lottoNumber -> lottoNumber.value in winningLotto.mainLottoNumbers.map { it.value } }

    fun checkMatchedBonusLottoNumber(lotto: Lotto, winningLotto: WinningLotto): Boolean =
        winningLotto.bonusLottoNumber.value in lotto.lottoNumbers.map { lottoNumber -> lottoNumber.value }
}
