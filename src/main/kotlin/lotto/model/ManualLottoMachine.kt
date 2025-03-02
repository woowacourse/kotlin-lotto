package lotto.model

class ManualLottoMachine : LottoMachine {
    override fun generate(
        manualTicket: List<List<Int>>,
        quantity: Int,
    ): List<Lotto> = manualTicket.map { numbers -> Lotto.from(numbers) }
}
