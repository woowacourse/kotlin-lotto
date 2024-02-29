package lotto.model

class BonusNumber(
    private val bonusNumber: LottoNumber
) {

    fun getBonusNumber(): Int {
        return bonusNumber.getNumber()
    }
}
