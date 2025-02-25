package lotto.domain

class Lotto(val lottoNums: List<LottoNumber>) {
    init {
        require(lottoNums.size == DEFAULT_LOTTO_SIZE) { "로또는 6개의 숫자를 가져야 합니다" }
        require(lottoNums.size == lottoNums.toSet().size) { "로또 번호는 중복될 수 없습니다" }
    }

    // 순위 반환하기
    fun getRank(winningLotto: WinningLotto): Rank {
        val matchCount = compareWithWinningLotto(winningLotto.winningLotto)
        val matchbonus = compareWithBonusNumber(winningLotto.bounusNumber)
        return Rank.valueOf(matchCount, matchbonus)
    }

    // 당첨 번호랑 비교하기
    fun compareWithWinningLotto(winningLotto: Lotto): Int {
        return lottoNums.count { it in winningLotto.lottoNums }
    }

    // 보너스 번호가 있는 지 확인하기
    fun compareWithBonusNumber(bonusNumber: LottoNumber): Boolean {
        return lottoNums.any { it == bonusNumber }
    }

    companion object {
        const val DEFAULT_LOTTO_SIZE = 6
    }
}
