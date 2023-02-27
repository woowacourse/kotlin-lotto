package model

class Count(val number: Int) {

    init {
        require(number >= MINIMUM_COUNT) { MINIMUM_COUNT_ERROR_MESSAGE }
    }

    companion object {
        private const val MINIMUM_COUNT = 1
        private const val MINIMUM_COUNT_ERROR_MESSAGE = "[ERROR] 로또가 존재하지 않습니다"
    }
}
