package lotto.domain

class TicketCount(val count: Int) {
    init {
        checkCount()
    }

    private fun checkCount() {
        require(count >= 0) {
            "${COUNT_ERROR_MESSAGE}\n" +
                "오류값 : $count"
        }
    }

    companion object {
        private const val COUNT_ERROR_MESSAGE = "로또 개수는 음수일 수 없습니다."
    }
}
