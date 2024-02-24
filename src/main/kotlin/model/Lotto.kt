package model

import lotto.model.LottoNumbers

data class Lotto(val lottoNumbers: LottoNumbers) {
    fun getMatchCount(winningNumbers: LottoNumbers): Int {
        return lottoNumbers.numbers.count { it -> it.number in winningNumbers.numbers.map { it.number } }
    }

    fun getMatchBonus(bonusNumber: Int): Boolean = lottoNumbers.numbers.any { it.number == bonusNumber }
}
