package lotto.entity

class Bonus(val value: Int) {
    init {
        require((Lotto.MINIMUM_LOTTO_NUMBER..Lotto.MAXIMUM_LOTTO_NUMBER).contains(value)) { ERROR_MESSAGE_RANGE_1_TO_45 }
    }

    companion object {
        private const val ERROR_MESSAGE_RANGE_1_TO_45 = "보너스 번호는 1에서 45 사이의 숫자여야 합니다"
    }
}
