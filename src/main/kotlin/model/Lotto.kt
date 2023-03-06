package model

class Lotto(val ticket: List<LottoNumber>) {

    init {
        require(ticket.size == LOTTO_NUMBER_COUNT_RULE) { ERROR_LOTTO_SIZE }
        require(ticket.toSet().size == LOTTO_NUMBER_COUNT_RULE) { ERROR_LOTTO_NUMBER_DUPLICATION }
    }

    fun getMatchOfNumber(winningNumber: Lotto): Int {
        val winningNumbers = winningNumber.ticket.map { ticket -> ticket.value }

        return ticket.filter { lottoNumber ->
            winningNumbers.contains(lottoNumber.value)
        }.size
    }

    fun isMatchBonus(winningNumber: LottoNumber): Boolean {
        val lotto = ticket.map { lotto -> lotto.value }
        return lotto.contains(winningNumber.value)
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT_RULE = 6
        const val ERROR_LOTTO_SIZE = "[ERROR] 로또 번호의 개수가 6개가 아닙니다"
        const val ERROR_LOTTO_NUMBER_DUPLICATION = "[ERROR] 로또 번호가 중복입니다"
    }
}
