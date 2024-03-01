package lotto.model

class ManualLottoMachine(private val manualLottoNumbers: List<List<Int>>) {
    fun makeManualTickets(): List<UserLottoTicket> {
        val manualLottoTickets =
            manualLottoNumbers.map {
                UserLottoTicket(it.sorted().map { LottoNumber.makeLottoNumber(it) })
            }
        return manualLottoTickets
    }
}
