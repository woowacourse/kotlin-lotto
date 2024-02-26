package lotto.model

class BonusNumber(
    private val bonusNumber: Int
) {
    init {
        if (bonusNumber !in Lotto.LOTTO_NUM_RANGE)
            throw ExceptionManager.getException(UserEvent.BonusEvent.InvalidNumRange)
    }

    fun getBonusNumber() : Int {
        return bonusNumber
    }
}
