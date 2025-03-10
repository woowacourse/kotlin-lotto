package lotto.model

interface LottoMachine {
    fun generate(
        manualTicket: List<List<Int>>,
        quantity: Int,
    ): List<Lotto>
}
