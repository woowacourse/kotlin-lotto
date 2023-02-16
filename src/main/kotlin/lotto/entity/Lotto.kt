package lotto.entity

class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_COUNT) { ERROR_MESSAGE_WIN_NUMBER_IS_SIX }
        require(numbers.intersect(numbers).size == LOTTO_COUNT) { ERROR_MESSAGE_DUPLICATED_NUMBER }
    }

    companion object {
        private const val ERROR_MESSAGE_WIN_NUMBER_IS_SIX = "당첨 번호는 6개여야 합니다"
        private const val ERROR_MESSAGE_DUPLICATED_NUMBER = "당첨 번호는 서로 중복될 수 없습니다"
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        const val LOTTO_MINIMUM_RANGE = 0
        const val LOTTO_COUNT = 6
    }
}
