package lotto.model

class WinningNumber(
    private val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!lotto.contains(bonusNumber))
    }

    fun getWinning(): Lotto {
        return lotto
    }

    fun getBonusNumber(): LottoNumber {
        return bonusNumber
    }
}
