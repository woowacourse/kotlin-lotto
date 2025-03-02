package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoBundleTest {
    @Test
    fun `로또 번들에 로또가 0개 이하라면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoBundle(listOf())
        }
    }

    @Test
    fun `로또 번들은 로또를 1개 이상 가지고 있어야 한다`() {
        assertDoesNotThrow {
            LottoBundle(List(1) { Lotto(listOf(1, 2, 3, 4, 5, 6)) })
        }
    }

    @Test
    fun `크기가 1인 로또 번들과 크기가 3인 로또 번들을 결합하면 크기가 4인 로또 번들이 되야한다`() {
        val lottoBundle1 = LottoBundle(List(1) { Lotto(listOf(1, 2, 3, 4, 5, 6)) })
        val lottoBundle2 = LottoBundle(List(3) { Lotto(listOf(1, 2, 3, 4, 5, 6)) })

        val actual = LottoBundle.combine(lottoBundle1, lottoBundle2).lottos.size
        val expected = 4
        assertThat(actual).isEqualTo(expected)
    }
}
