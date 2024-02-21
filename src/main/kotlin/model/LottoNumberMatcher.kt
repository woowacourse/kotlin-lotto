package model

class LottoNumberMatcher {
    fun matchCount(targetLotto: Lotto, winningLotto: Lotto): Int {
        return (targetLotto.numbers.intersect(winningLotto.numbers.toSet())).size
    }

    fun bonusCount(targetLotto: Lotto, bonusNumber: LottoNumber): Boolean {
        return targetLotto.numbers.contains(bonusNumber)
    }
}
