class WinningNumbers(
    val catchNumbers: Set<Int>,
    val bonusNumber: Int
) {
    init {
        require(
            catchNumbers.all { number ->
                number in MINIMUM_NUMBER..MAXIMUM_NUMBER
            }
        ) {
            CATCH_NUMBER_RANGE_ERROR
        }

        require(catchNumbers.size == CATCH_NUMBERS_COUNT) {
            CATCH_NUMBER_COUNT_ERROR
        }
        require(!catchNumbers.contains(bonusNumber)) {
            OVERLAPPED_ERROR
        }
    }

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private const val CATCH_NUMBERS_COUNT = 6
        private const val CATCH_NUMBER_RANGE_ERROR = "[ERROR] 당첨 번호는 1이상 45이하의 숫자로 이루어져야 합니다."
        private const val CATCH_NUMBER_COUNT_ERROR = "[ERROR] 당첨 번호는 6개의 숫자로 이루어져야 합니다."
        private const val OVERLAPPED_ERROR = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다."
    }
}
