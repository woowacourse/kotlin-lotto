package lotto.domain

class LottoFactory(private val generateStrategy: GenerateStrategy) {
    fun generate(): LottoTicket {
        return generateStrategy.generate()
    }
}
