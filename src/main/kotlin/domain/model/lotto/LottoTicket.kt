package domain.model.lotto

class LottoTicket(private val numbers: List<Int>) : List<Int> by numbers {
    init {
        require(numbers.distinct().size == numbers.size) {
            DUPLICATE_NUMBERS
        }
        require(numbers.size == NUMBER_COUNT) {
            NUMBER_COUNT_ERROR
        }
    }

    companion object {
        private const val NUMBER_COUNT = 6
        private const val DUPLICATE_NUMBERS = "[ERROR] 중복된 숫자가 존재합니다."
        private const val NUMBER_COUNT_ERROR = "[ERROR] 로또 번호는 6개의 숫자로 이루어져야 합니다."
    }
}
