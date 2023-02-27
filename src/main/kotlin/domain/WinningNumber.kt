package domain

class WinningNumber(private val lottoNumbers: List<LottoNumber>, private val bonusNumber: LottoNumber) :
    Lotto(lottoNumbers) {
    init {
        require(!this.contains(bonusNumber)) { BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE }
    }

    fun getRank(lotto: Lotto): Rank {
        val countOfMatch = lotto.getCountOfMatch(this)
        val matchBonus = lotto.contains(bonusNumber)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    companion object {
        const val BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE = "[ERROR] 당첨번호가 보너스 번호를 포함하고 있습니다."
    }
}
