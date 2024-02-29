package lotto.model

class ManualLottoMachine(private val manualLottoNumbers: List<List<Int>>) {
    fun makeManualTickets(): List<UserLottoTicket> {
        return manualLottoNumbers.map {
            UserLottoTicket(it.map { LottoNumber.makeLottoNumber(it) })
        }
    }
}
