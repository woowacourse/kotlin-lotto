package domain

class LottoNumber(val number: Int) {
    init {
        require(number in 1..45) { INPUT_LOTTO_RANGE_ERROR_MESSAGE }
    }

    companion object {
        const val INPUT_LOTTO_RANGE_ERROR_MESSAGE = "당첨 번호의 범위는 1에서 45 사이의 숫자여야 합니다."
    }
}
