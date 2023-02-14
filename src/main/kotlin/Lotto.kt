class Lotto(val numbers: List<Int>?) {

    init {
        require( numbers != null) { INPUT_LOTTO_NULL_ERROR_MESSAGE }
    }

    companion object {
        const val INPUT_LOTTO_NULL_ERROR_MESSAGE = "당첨 번호가 입력되지 않았습니다."
    }
}