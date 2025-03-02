package lotto.model

interface LottoMachine {
    fun generate(
        manualTicket: List<List<Int>> = emptyList(),
        quantity: Int = manualTicket.size,
    ): List<Lotto>
}
