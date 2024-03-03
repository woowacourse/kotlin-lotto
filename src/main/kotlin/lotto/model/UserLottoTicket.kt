package lotto.model

@JvmInline
value class UserLottoTicket private constructor(val userLottoTicket: List<LottoNumber>) {
    init {
        require(userLottoTicket.size == LOTTO_TICKET_SIZE) {
            "[ERROR] 개수가 틀림"
        }
        require(userLottoTicket.map { it.number }.distinct().size == LOTTO_TICKET_SIZE) {
            "[ERROR] 중복이 있음"
        }
    }

    private fun countOfMatchWithNumbers(numbers: List<LottoNumber>): Int {
        return numbers.intersect(userLottoTicket.toSet()).size
    }

    private fun isNumInTicket(number: LottoNumber): Boolean {
        return userLottoTicket.contains(number)
    }

    fun getRank(
        numbers: List<LottoNumber>,
        number: LottoNumber,
    ): Rank {
        val countOfMatchWithWinningNumbers = countOfMatchWithNumbers(numbers)
        val isBonusInTicket = isNumInTicket(number)
        return Rank.decideRank(countOfMatchWithWinningNumbers, isBonusInTicket)
    }

    companion object {
        private const val LOTTO_TICKET_SIZE = 6

        fun of(lottoNumbers: List<LottoNumber>): UserLottoTicket {
            val sortedLottoNumbers = lottoNumbers.sortedBy { it.number }
            return UserLottoTicket(sortedLottoNumbers)
        }
    }
}
