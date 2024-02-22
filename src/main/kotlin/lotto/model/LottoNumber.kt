package lotto.model

data class LottoNumber(val number: Int) {

    init {
        require(number in MIN_RANGE_NUM..MAX_RANGE_NUM) {
            "${MIN_RANGE_NUM}부터 $MAX_RANGE_NUM 사이의 숫자를 입력해 주세요."
        }
    }

    companion object {
        private const val MIN_RANGE_NUM = 1
        private const val MAX_RANGE_NUM = 45
    }

}
