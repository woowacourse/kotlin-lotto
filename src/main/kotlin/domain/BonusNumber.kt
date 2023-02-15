package domain

class BonusNumber(val number: Int?) {

    init {
        require(number in 1..45) { BONUS_NUMBER_RANGE_ERROR }
    }

    companion object {
        const val BONUS_NUMBER_RANGE_ERROR = "보너스 번호는 1에서 45 사이여야 합니다."
    }
}
