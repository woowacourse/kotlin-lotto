package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class LottoFactoryTest {
    @Test
    internal fun `개수에 맞는 로또 티켓들 발급`() {
        val firstLottoTicket = LottoTicket((1..6).map { LottoNumber.from(it) })
        val secondLottoTicket = LottoTicket((2..7).map { LottoNumber.from(it) })
        val purchaseAmount = PurchaseAmount(2000)
        val generateStrategy: GenerateStrategy =
            TestGenerateStrategy(mutableListOf(firstLottoTicket, secondLottoTicket))
        val lottoFactory = LottoFactory(generateStrategy)

        val lottoTickets = lottoFactory.publishLottoTickets(purchaseAmount)

        assertAll("로또 티켓들 발급",
            {assertThat(lottoTickets.lottoTickets.size).isEqualTo(2)},
            {assertThat(lottoTickets.lottoTickets[0]).isEqualTo(firstLottoTicket)},
            {assertThat(lottoTickets.lottoTickets[1]).isEqualTo(secondLottoTicket)}
        )
    }
}
