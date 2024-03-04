package lotto.model

class ManualLottoMachine(private val numbers: List<List<Int>>) : LottoMachine {
    override fun make(): List<UserLottoTicket> {
        return numbers.map { UserLottoTicket.of(it.map { LottoNumber.of(it) }) }
    }
}
