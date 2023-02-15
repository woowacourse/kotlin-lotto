package lotto.entity

class WinNumber(val value: List<Int>) {
    init {
        require(value.size == Lotto.LOTTO_COUNT) { ERROR_MESSAGE_WIN_NUMBER_IS_SIX }
        require(value.all { (Lotto.MINIMUM_LOTTO_NUMBER..Lotto.MAXIMUM_LOTTO_NUMBER).contains(it) }) { ERROR_MESSAGE_RANGE_1_TO_45 }
        require(value.intersect(value.toSet()).size == Lotto.LOTTO_COUNT) { ERROR_MESSAGE_DUPLICATED_NUMBER }
    }

    companion object {
        private const val ERROR_MESSAGE_WIN_NUMBER_IS_SIX = "당첨 번호는 6개여야 합니다"
        private const val ERROR_MESSAGE_DUPLICATED_NUMBER = "당첨 번호는 서로 중복될 수 없습니다"
        private const val ERROR_MESSAGE_RANGE_1_TO_45 = "당첨 번호는 1에서 45 사이의 숫자여야 합니다"
    }
}
