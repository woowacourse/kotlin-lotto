package lotto.model

class LottoNumbers(
    val numberList: List<LottoNumber>,
) {
    init {
        require(numberList.size == LOTTO_NUMBER_SIZE) {
            SIZE_ERROR_MESSAGE
        }
        require(numberList.distinct().size == numberList.size) {
            DUPLICATE_ERROR_MESSAGE
        }
    }

    companion object {
        const val LOTTO_NUMBER_SIZE = 6
        private const val SIZE_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개여야 합니다."
        private const val DUPLICATE_ERROR_MESSAGE = "[ERROR] 로또 번호는 중복될 수 없습니다."
    }
}
