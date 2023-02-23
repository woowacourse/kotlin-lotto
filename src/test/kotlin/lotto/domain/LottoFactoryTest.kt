package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoFactoryTest {
    private val lottoFactory = LottoFactory()

    @Test
    fun `로또 번호를 자동으로 6개 생성한다`() {
        assertThat(lottoFactory.createAutoLotto().lottoNumbers).hasSize(6)
    }

    @Test
    fun `로또 번호를 수동으로 6개 생성한다`() {
        assertThat(lottoFactory.createManualLotto(listOf(1, 2, 3, 4, 5, 6)).lottoNumbers).hasSize(6)
    }
}
