package model

class Lotto(val lottoNumbers: List<Int>) {
    init {
        require(lottoNumbers.size == LOTTO_NUMBER_COUNT_RULE) { LOTTO_NUMBER_COUNT_ERROR_MESSAGE }
        require(lottoNumbers.max() <= MAXIMUM_LOTTO_NUMBER && lottoNumbers.min() >= MINIMUM_LOTTO_NUMBER) { LOTTO_NUMBER_RANGE_ERROR_MESSAGE }
        require(lottoNumbers.toSet().size == LOTTO_NUMBER_COUNT_RULE) { LOTTO_NUMBER_DUPLICATE_ERROR_MESSAGE }
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT_RULE = 6
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private const val LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "[ERROR] 로또 번호의 개수가 6개가 아닙니다"
        private const val LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호가 1~45가 아닙니다"
        private const val LOTTO_NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 로또 번호가 중복입니다"
    }
}
