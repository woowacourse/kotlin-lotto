package lotto.model

class WinningNumber(
    private val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    fun getWinning(): Lotto {
        return lotto
    }

    fun getBonusNumber(): LottoNumber {
        return bonusNumber
    }
}
