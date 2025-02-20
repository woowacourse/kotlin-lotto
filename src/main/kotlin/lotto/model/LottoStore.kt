package lotto.model

class LottoStore {
    fun getTickets(
        input: Int,
        lottoGenerator: LottoGenerator,
    ): List<Lotto> {
        return lottoGenerator.generate(input)
    }
}
