package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoFactoryTest {
    @Test
    internal fun `로또 티켓 생성`() {
        val lottoNumbers: List<LottoNumber> = (1..6).map { LottoNumber.of(it) }
        val generateStrategy: GenerateStrategy = TestGenerateStrategy(lottoNumbers)
        val lottoFactory = LottoFactory(generateStrategy)

        val lottoTicket: LottoTicket = lottoFactory.generate()

        assertThat(lottoTicket.lottoNumbers).isEqualTo(lottoNumbers)
    }
}
