class Lotto(val numbers: List<LottoNumber>) {

    init {
        require(numbers.size == LOTTO_NUMBERS_COUNT) { LOTTO_NUMBERS_COUNT_ERROR }
    }

    companion object {
        const val LOTTO_NUMBERS_COUNT = 6
        const val LOTTO_NUMBERS_COUNT_ERROR = "[ERROR] 로또의 번호가 6개가 아닙니다."
    }
}
