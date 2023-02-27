package domain

class CountOfMatch(val count: Count) {
    init {
        require(count.value <= MAXIMUM_COUNT_OF_MATCH_NUMBER) { MAXIMUM_COUNT_OF_MATCH_ERROR }
    }

    companion object {
        private const val MAXIMUM_COUNT_OF_MATCH_NUMBER = 6
        private const val MAXIMUM_COUNT_OF_MATCH_ERROR = "[ERROR] 일치하는 개수는 6을 넘을 수 없습니다!"
    }
}
