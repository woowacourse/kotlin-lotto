package lotto.domain

class LottoNumber(val number: Int) {
    init {
        require(number in (1..45)) { LOTTO_NUMBER_RANGE_ERROR }
    }

    companion object {
        private const val LOTTO_NUMBER_RANGE_ERROR = "로또 번호는 1과 45 사이여야 합니다."
    }
}
