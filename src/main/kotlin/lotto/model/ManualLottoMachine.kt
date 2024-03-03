package lotto.model

class ManualLottoMachine(private val numbers: List<List<Int>>) : LottoMachine {
    init {
        numbers.map { require(it.size == NUMBER_OF_LOTTO_NUM) { "[ERROR] 번호 개수 틀림" } }
        numbers.map { require(it.distinct().size == it.size) { "[ERROR] 중복이 있음" } }
    }

    override fun make(): List<UserLottoTicket> {
        return numbers.map { UserLottoTicket.wrap(it.map { LottoNumber.makeLottoNumber(it) }) }
    }

    companion object {
        private const val NUMBER_OF_LOTTO_NUM = 6
    }
}
