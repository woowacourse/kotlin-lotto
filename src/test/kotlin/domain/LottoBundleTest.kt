package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoBundleTest {

    @Test
    fun `수동으로 로또를 발행`() {
        // given
        val lottoBundle = LottoBundle()
        val lotto = Lotto(listOf("1", "2", "3", "4", "5", "6"))

        // when
        lottoBundle.manualGenerate(lotto)

        // then
        assertThat(lottoBundle.lottos[0]).isEqualTo(lotto)
    }

    @Test
    fun `자동으로 발행해야할 로또 개수만큼 발행`() {
        // given
        val lottoBundle = LottoBundle()
        val lotto = Lotto(listOf("1", "2", "3", "4", "5", "6"))

        // when
        lottoBundle.autoGenerate(5) { lotto }

        // then
        assertThat(lottoBundle.lottos.size).isEqualTo(5)
    }
}
